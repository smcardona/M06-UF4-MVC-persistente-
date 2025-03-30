package com.iticbcn.apio.controllers;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.iticbcn.apio.models.Book;
import com.iticbcn.apio.models.User;
import com.iticbcn.apio.services.BookService;


@Controller
@SessionAttributes("user")
public class BookController {

  @Autowired
  BookService bookService;

  @GetMapping("/error")
  public String errorHandler() {
    return "error";
  }

  @GetMapping("/")
  public String welcome(Model model) {
    model.addAttribute("message", "Benvingut a la aplicació de gestió de llibres");
    return "welcome";
  }

  @GetMapping("/index")
  public String index(@ModelAttribute("user") User user, Model model) {
    return "index";
  }

  @GetMapping("/insert")
  public String inserir(@ModelAttribute("user") User user, Model model) {
    return "insertForm";
  }

  @PostMapping("/insert")
  public String inserir(@ModelAttribute("user") User user, Book llibre, Model model) {

    if (!bookService.isValidISBN(llibre.getISBN())) {
      model.addAttribute("errMessage", "Format de ISBN Invalid, ha de ser: NNN-NN-NNN-NNNN-N");
      return "insertForm";
    }

    // Agrega el libro a la base de datos
    try {
      bookService.save(llibre);
    } catch (Exception e) {
      model.addAttribute("errMessage", "Error al guardar el llibre: " + e.getMessage());
      return "insertForm";
    }

    Set<Book> llibres = bookService.findAll();

    model.addAttribute("llibres", llibres);
    
    return "resultList";
  
  }

  @GetMapping("/list") 
  public String consulta(@ModelAttribute("user") User user,Model model) {

    Set<Book> llibres = bookService.findAll();

    model.addAttribute("llibres", llibres);
    
    return "resultList";
  }
  
  @GetMapping("/find")
  public String inputCerca(@ModelAttribute("user") User user, Model model) {

    return "getByID"; // Formulario de ID para buscar

  }

  @PostMapping("/find")
  public String cercaId(@ModelAttribute("user") User user,
                        @RequestParam(name = "idLlibre", required = false) String idLlibre, 
                        Model model) {
      
      int idLlib = 0;
      String message = null;

      try {
        idLlib = Integer.parseInt(idLlibre);
        Optional<Book> llibre = bookService.findById(idLlib);

        if(llibre.isPresent()) {

          model.addAttribute("llibre", llibre.get());

        } else {
          message = "No hi ha cap llibre amb aquesta id";
        }

      } catch (Exception e) {
        message = "La id de llibre ha de ser un nombre enter";
      } 
      
      model.addAttribute("llibreErr", message);

      return "getByID"; // Resultado de libre y formulario a la vez

  }

  
}
