package co.edu.uniquindio.AseguradoraLAYO.dto.HogarDTOs;

import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import java.time.LocalDateTime;

public record ObtenerHogarDTO(
        String id,
        Aseguradora aseguradora,
        String nombre,
        String cedula,
        String correo,
        String telefono,
        String direccion,
        LocalDateTime fechaNacimiento,
        float valorComercial,
        float valorElectrico,
        float valorMuebles
) {}

