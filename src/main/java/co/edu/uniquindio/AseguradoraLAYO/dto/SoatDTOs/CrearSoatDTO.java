package co.edu.uniquindio.AseguradoraLAYO.dto.SoatDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record CrearSoatDTO(
        @NotBlank(message = "La placa es obligatoria") @Length(max = 6, message = "Ingrese una placa valida") String placa,
        @NotBlank(message = "El tamano del nombre no es adecuado") @Length(max = 20) String nombre,
        @NotBlank(message = "El tamano de la cedula no es adecuado") @Length(max = 10) String cedula,
        @Length(max = 10, message = "Ingrese un telefono valido") String telefono,
        @Length(max = 30, message = "Ingrese una direccion valida") String direccion,
        @NotBlank @Length(max = 30, message = "Ingrese un correo valido") @Email String correo
) {
}
