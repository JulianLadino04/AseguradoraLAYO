package co.edu.uniquindio.AseguradoraLAYO.dto.CuentaDTOs;

import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Rol;
import org.bson.types.ObjectId;

public record ItemCuentaDTO(
        ObjectId id,
        String nombre,
        String email,
        String telefono,
        EstadoCuenta estadoCuenta,
        Rol rol
) {
}
