package com.iticbcn.apio.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.iticbcn.apio.models.User;
import com.iticbcn.apio.services.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {


  @Autowired
  private UserService userService;

  
  @ModelAttribute("user")
  public User initUser() {
      return new User(); // Inicia la instancia de User que se pasa por los atributos de sesion
  }

  @GetMapping("/login")
  public String showLoginForm() {
      return "userLogin"; // formulario de login
  }

  @PostMapping("/login")
  public String loginUser(@RequestParam String username, 
                          @RequestParam String password,
                          @ModelAttribute("user") User user,
                          Model model) {

    Optional<User> validUser = userService.validateUser(username, password);
    if (validUser.isPresent()) {
      user.setUserId(validUser.get().getUserId());
      user.setUsername(validUser.get().getUsername());
      user.setPassword(validUser.get().getPassword());
      
      // Redirect se usa para mandar haciendo una peticion GET, y no devolver el html directamente despues de haber hecho un POST
      return "redirect:/index"; 

    } else {
      model.addAttribute("error", "Credencials incorrectes");
      return "redirect:/user/login";
    }
  }

  @GetMapping("/sign-in")
  public String registerForm() {
    return "userSignIn";
  }

  @PostMapping("/sign-in")
  public String registerUser( @RequestParam String username, 
                              @RequestParam String password,
                              @ModelAttribute("user") User user,
                              Model model) {

    try {

      User nou = userService.registerUser(username, password);
      user.setUserId(nou.getUserId());
      user.setUsername(nou.getUsername());
      user.setPassword(nou.getPassword());

      model.addAttribute("success", "Usuari creat correctament, ara inicia sesió");

      return "userLogin";

    } catch (Exception e) {

      model.addAttribute("error", "Error: "+e.getMessage());

      return "userSignIn";
      
    }

  }


  @GetMapping("/logout")
  public String logoutUser(@ModelAttribute("user") User user) {
    user.setUsername(null); // Limpiamos la sesión
    user.setPassword(null);
    user.setUserId(null);
    return "redirect:/user/login";
  }
}
