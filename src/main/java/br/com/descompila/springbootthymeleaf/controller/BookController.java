package br.com.descompila.springbootthymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.descompila.springbootthymeleaf.model.Book;
import br.com.descompila.springbootthymeleaf.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class BookController {
    
    private static final String USER_LOGIN = "userLogin";
    
    @Autowired
    private BookService bookService;

    @GetMapping("books")//o name é usado somente quando o valor da solicitacao for diferente da variavel
    public String getBookPage(@RequestParam(required = false, name = "login") String login, 
                              @RequestParam(required = false) String email, 
                              Model model,
                              HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        if(login != null && !login.isEmpty()){
            session.setAttribute(USER_LOGIN, login);
        }

        String userLogin = (String) session.getAttribute(USER_LOGIN);

        model.addAttribute(USER_LOGIN, userLogin);

        List<Book> books = bookService.getAllBooksByLogin(userLogin);

        model.addAttribute("userBooks", books);

        return "book_page";
    }
    
}
