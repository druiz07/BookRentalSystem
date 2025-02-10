package com.example.bookSystem.controllers;

import com.example.bookSystem.models.Book;
import com.example.bookSystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {
    BookService bookService;
    @Autowired
    public BookController(BookService service)
    {
        this.bookService= service;
    }


    public ResponseEntity<Book> addBook(@RequestBody Map<String, String> request) {
        String title = request.get("title");
        String author = request.get("author");
        return ResponseEntity.ok(bookService.addBook(title, author));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());

    }

    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        return ResponseEntity.ok(bookService.getAvailableBooks());
    }
    @PutMapping("/rent")
    public ResponseEntity<String> rentBook(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String title = request.get("title");

        boolean success = bookService.rentBook(email, title);
        if (success) return ResponseEntity.ok("El libro se ha rentado");
        return ResponseEntity.badRequest().body("El libro no está disponible o no existe.");
    }
    @PutMapping("/return")
    public ResponseEntity<String> returnBook(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String title = request.get("title");

        boolean success = bookService.returnBook(email, title);
        if (success) return ResponseEntity.ok("Libro devuelto con éxito.");
        return ResponseEntity.badRequest().body("El libro no está rentado por este usuario o no existe.");
    }
}
