package co.edu.uniquindio.AseguradoraLAYO.dto.CuentaDTOs;

import org.bson.types.ObjectId;

public record InformacionCuentaDTO(
        String cedula,
        String nombre,
        String telefono,
        String direccion,
        String correo
) {
}
