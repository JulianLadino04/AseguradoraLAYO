package co.edu.uniquindio.AseguradoraLAYO.dto.VidaDTOs;

import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import java.time.LocalDateTime;

public record ObtenerVidaDTO(
        String id,
        Aseguradora aseguradora,
        String nombre,
        String cedula,
        String correo,
        String telefono,
        String direccion,
        String ocupacion,
        LocalDateTime fechaNacimiento
) {}

