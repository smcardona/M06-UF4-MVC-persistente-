package com.iticbcn.apio.services;

import java.util.Optional;

import com.iticbcn.apio.models.User;

public interface UserService {
  
  User registerUser(String username, String password);

  Optional<User> validateUser(String username, String password);

}
