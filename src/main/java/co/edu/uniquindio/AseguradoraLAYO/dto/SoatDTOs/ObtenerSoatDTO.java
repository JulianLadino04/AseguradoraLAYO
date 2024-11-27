package co.edu.uniquindio.AseguradoraLAYO.dto.SoatDTOs;

import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.TipoVehiculo;

public record ObtenerSoatDTO(
        String id,
        String numeroPlaca,
        String nombre,
        String cedula,
        String correo,
        String telefono,
        String direccion,
        Aseguradora aseguradora,
        TipoVehiculo tipo
) { }
