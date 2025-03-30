package com.iticbcn.apio.services;

import com.iticbcn.apio.models.User;
import com.iticbcn.apio.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

    

  // Registrar un nuevo usuario con contraseña encriptada
  public User registerUser(String username, String password) {
      if (userRepository.findByUsername(username).isPresent()) {
          throw new RuntimeException("El usuari ja existeix");
      }
      User user = new User();
      user.setUsername(username);
      user.setPassword(password);
      return userRepository.save(user);
  }

  // Validar credenciales del usuario
  public Optional<User> validateUser(String username, String password) {
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isPresent() && password.equals(user.get().getPassword())) {
      return user;
    }
    else {
      return Optional.empty();
    }
  }
}
