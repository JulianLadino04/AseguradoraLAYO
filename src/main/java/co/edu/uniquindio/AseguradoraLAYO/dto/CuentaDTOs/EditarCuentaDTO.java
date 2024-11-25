package co.edu.uniquindio.AseguradoraLAYO.dto.CuentaDTOs;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EditarCuentaDTO(
        @NotBlank String id,
        @NotBlank @Length( max = 50) String nombre,
        @NotBlank @Length( max = 50) String telefono,
        @NotBlank @Length( max = 50) String direccion,
        @NotBlank @Length( max = 50) String email
) {
}