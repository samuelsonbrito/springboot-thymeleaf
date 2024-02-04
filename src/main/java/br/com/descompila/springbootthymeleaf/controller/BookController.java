package br.com.descompila.springbootthymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.descompila.springbootthymeleaf.model.Book;
import br.com.descompila.springbootthymeleaf.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/books")
public class BookController {
    
    private static final String USER_LOGIN = "userLogin";
    
    @Autowired
    private BookService bookService;

    @GetMapping
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

    @GetMapping("/create")
    public String getCreateBookPage(Model model) {
        model.addAttribute("newBook", new Book());
        return "create_book_page";
    }

    @PostMapping("/createBook")
    public String createBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{title}")
    public String getEditBookPage(Model model, @PathVariable String title) {
        Book byTitle = bookService.findByTitleAndDelete(title);
        model.addAttribute("bookToEdit", byTitle);
        return "edit_book_page";
    }

    @PostMapping("/editBook")
    public String editBook(@ModelAttribute Book book) {
        bookService.edit(book);
        return "redirect:/books";
    }
    
    @GetMapping("/delete/{title}")
    public String delete(@PathVariable String title) {
        bookService.delete(title);
        return "redirect:/books";
    }
    
}
