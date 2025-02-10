package com.example.bookSystem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//Usamos un DTO puesto que no queremos tener que introducir un libro entero para rentar
//O para devolver un libro , solo los campos que nos piden
//Podriamos usar un map , pero usamos un DTO con que tiene que estar @Valid puesto que tenemos
//Constraints aplicadas
//Un DTO no es mas que los campos del libro que necesitemos con getters y setters
public class BookRentalDTO {

    @Email(message = "El email debe ser válido")
    @NotBlank(message = "El email es obligatorio")
    private String rentedBy;

    @NotBlank(message = "El título del libro es obligatorio")

    private String title;

    // Getters y Setters

    public String getRentedBy() {
        return rentedBy;
    }

    public void setRentedBy(String rentedBy) {
        this.rentedBy = rentedBy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
