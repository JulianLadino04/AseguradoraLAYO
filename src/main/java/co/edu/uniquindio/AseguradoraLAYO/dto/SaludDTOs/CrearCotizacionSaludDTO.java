package co.edu.uniquindio.AseguradoraLAYO.dto.SaludDTOs;

import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record CrearCotizacionSaludDTO(
        @NotBlank(message = "La aseguradora es obligatoria") @Length(max = 30, message = "Ingrese una aseguradora válida") Aseguradora aseguradora,
        @NotBlank(message = "El nombre es obligatorio") @Length(max = 30, message = "El nombre debe tener máximo 30 caracteres") String nombre,
        @NotBlank(message = "La cédula es obligatoria") @Length(max = 10, message = "La cédula debe tener máximo 10 caracteres") String cedula,
        @NotBlank(message = "El correo es obligatorio") @Email(message = "Ingrese un correo válido") @Length(max = 30, message = "El correo debe tener máximo 30 caracteres") String correo,
        @Length(max = 10, message = "El teléfono debe tener máximo 10 caracteres") String telefono,
        @NotBlank(message = "La dirección es obligatoria") @Length(max = 50, message = "La dirección debe tener máximo 50 caracteres") String direccion,
        LocalDateTime fechaNacimiento
) {
}

