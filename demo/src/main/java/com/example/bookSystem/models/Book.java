package com.example.bookSystem.models;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

//Como estamos trabajando con un sistema de libros
//Cada entidad/modelo que gestionamos es un libro con sus atributor

public class Book {

   private Long id;
   @NotBlank(message = "El título no puede estar vacío")
   private String title;

   private boolean isRented;

   private static long counter=1;

   @NotBlank(message = "El autor no puede estar vacío")
   private String author;

   @Email (message = "El correo tiene ue ser un correo valido")
   private String rentedBy;



   public Book(String title, String author)
   {
      this.title=title;
      this.author=author;
      this.isRented=false;
      this.id=counter++;
      this.rentedBy=null;

   }
   public String getTitle(){return this.title;}
   public boolean isRented(){return  this.isRented;}

   public String getRentedBy() { return rentedBy; }

   public boolean rentBook(String userEmail) {
      if (!isRented) {
         this.isRented = true;
         this.rentedBy = userEmail;
         return true;
      }
      else return false;
   }

   public void returnBook() {
      this.isRented = false;
      this.rentedBy = null;
   }

}
