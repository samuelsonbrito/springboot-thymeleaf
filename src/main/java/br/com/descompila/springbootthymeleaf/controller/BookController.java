package br.com.descompila.springbootthymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BookController {
    
    @GetMapping("books")//o name é usado somente quando o valor da solicitacao for diferente da variavel
    public String getBookPage(@RequestParam(required = false, name = "login") String login, 
                              @RequestParam(required = false) String email, Model model) {
        
        model.addAttribute("userLogin", login);

        return "book_page";
    }
    
}
