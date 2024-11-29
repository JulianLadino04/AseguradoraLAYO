package co.edu.uniquindio.AseguradoraLAYO.dto.PeticionDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CrearPeticionDTO(
        @NotBlank(message = "El tamaño del nombre no es adecuado") @Length(max = 20, message = "El nombre debe tener máximo 20 caracteres") String nombre,
        @NotBlank(message = "La cédula es obligatoria") @Length(max = 10, message = "La cédula debe tener máximo 10 caracteres") String cedula,
        @NotBlank @Email(message = "Ingrese un correo válido") @Length(max = 30, message = "El correo debe tener máximo 30 caracteres") String correo,
        @Length(max = 10, message = "Ingrese un teléfono válido") String telefono,
        @Length(max = 30, message = "Ingrese una Aseguradora valida") String asegurar,
        @Length(max = 60, message = "El mensaje debe tener máximo 60 caracteres") String mensaje
) {

}
