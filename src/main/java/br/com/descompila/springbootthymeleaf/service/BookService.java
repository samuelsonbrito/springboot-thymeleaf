package br.com.descompila.springbootthymeleaf.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.descompila.springbootthymeleaf.model.Book;

@Service
public class BookService {
    
    private static List<Book> books;

    static {
        books = new ArrayList<>();
        books.add(new Book("Petrus Logus", 2014));
        books.add(new Book("Petrus Logus 2", 2015));
        books.add(new Book("Petrus Logus 3", 2016));
    }

    public List<Book> getAllBooksByLogin(String login){
        if(login != null){
            return books;
        }

        return books.stream().filter(book -> book.getYear() > 2015).toList();
    }

}
