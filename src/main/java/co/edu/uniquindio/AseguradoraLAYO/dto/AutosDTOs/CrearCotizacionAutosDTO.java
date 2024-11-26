package co.edu.uniquindio.AseguradoraLAYO.dto.AutosDTOs;

import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.TipoVehiculo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record CrearCotizacionAutosDTO(
        @NotBlank(message = "La aseguradora es obligatoria") @Length(max = 30, message = "Ingrese una aseguradora válida") Aseguradora aseguradora,
        @NotBlank(message = "La placa es obligatoria") @Length(max = 6, message = "Ingrese una placa válida") String placa,
        @NotBlank(message = "El tamaño del nombre no es adecuado") @Length(max = 20, message = "El nombre debe tener máximo 20 caracteres") String nombre,
        @NotBlank(message = "La cédula es obligatoria") @Length(max = 10, message = "La cédula debe tener máximo 10 caracteres") String cedula,
        @NotBlank @Email(message = "Ingrese un correo válido") @Length(max = 30, message = "El correo debe tener máximo 30 caracteres") String correo,
        @Length(max = 10, message = "Ingrese un teléfono válido") String telefono,
        @NotBlank(message = "La ciudad de circulación es obligatoria") @Length(max = 30, message = "La ciudad debe tener máximo 30 caracteres") String ciudadCirculacion,
        @NotBlank(message = "El tipo de vehículo es obligatorio") @Length(max = 15, message = "El tipo de vehículo debe tener máximo 15 caracteres") TipoVehiculo tipo,
        LocalDateTime fechaNacimiento
) {
}
