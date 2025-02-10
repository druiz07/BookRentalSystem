package com.example.bookSystem.services;

import com.example.bookSystem.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import  java.util.List;
import java.util.Optional;

@Service
public class BookService {

    List<Book> bookInventory;
    public List<Book> getAllBooks() {
        return bookInventory;
    }
    public Book addBook(String title , String author)
    {
        Book book= new Book(title,author);
        bookInventory.add(book);
        return  book;
    }
    public List<Book> getAvailableBooks() {
        List<Book>avaliableBooks=new ArrayList<Book>();
        for(Book b: bookInventory)
        {
            if(!b.isRented())avaliableBooks.add(b);
        }
        return avaliableBooks;
    }


    //Tanto para rentar como para devolver , necesitamos saber el titulo
    //y el email del usuario
    public boolean rentBook(String email, String title) {
        Optional<Book> bookOpt = bookInventory.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title) && !b.isRented())
                .findFirst();

        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            return book.rentBook(email);

        }
        return false;
    }
    public boolean returnBook(String email, String title) {
        Optional<Book> bookOpt = bookInventory.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title) && b.isRented() && b.getRentedBy().equals(email))
                .findFirst();

        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            book.returnBook();
            return true;
        }
        return false;
    }



}
