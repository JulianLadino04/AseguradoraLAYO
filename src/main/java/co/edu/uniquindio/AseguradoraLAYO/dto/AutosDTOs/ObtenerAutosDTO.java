package co.edu.uniquindio.AseguradoraLAYO.dto.AutosDTOs;

import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.TipoVehiculo;
import java.time.LocalDateTime;

public record ObtenerAutosDTO(
        String id,
        Aseguradora aseguradora,
        String numeroPlaca,
        String nombre,
        String cedula,
        String email,
        String telefono,
        String ciudadCirculacion,
        TipoVehiculo tipo,
        LocalDateTime fechaNacimiento
) {}
