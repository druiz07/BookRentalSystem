package com.example.bookSystem.controllers;

import com.example.bookSystem.models.Book;
import com.example.bookSystem.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.bookSystem.models.BookRentalDTO;

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

    //Importante saber que en todos los metodos tiene que haber una respuesta por parte
    //De nuestro controlador a la solicitud HTTP
    //La respuesta contiene dentre el objeto que se esta devolviendo y si esta bad o ok
    //En este caso no hay DTO , asi que se usa el map para manejar la request y
    //El formato es el especifico de la request
    @PostMapping
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

    //En este caso el programa sabe que se tiene que poner "rentedBy" y "title"
    //Puesto que al usar DTO , formatea el nombre de las variables que son
    // String rentedBy
    // String title
    @PatchMapping("/rent")
    public ResponseEntity<String> rentBook(@Valid @RequestBody BookRentalDTO book) {
        boolean success = bookService.rentBook(book.getRentedBy(), book.getTitle());
        if (success) return ResponseEntity.ok("El libro se ha rentado.");
        return ResponseEntity.badRequest().body("El libro no está disponible o no existe.");
    }

    @PatchMapping("/return")
    public ResponseEntity<String> returnBook(@Valid @RequestBody BookRentalDTO book) {
        boolean success = bookService.returnBook(book.getRentedBy(), book.getTitle());
        if (success) return ResponseEntity.ok("Libro devuelto con éxito.");
        return ResponseEntity.badRequest().body("El libro no está rentado por este usuario o no existe.");
    }
}
