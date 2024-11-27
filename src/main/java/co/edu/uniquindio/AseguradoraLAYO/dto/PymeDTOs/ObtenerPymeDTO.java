package co.edu.uniquindio.AseguradoraLAYO.dto.PymeDTOs;

import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.TipoInmueble;
import java.time.LocalDateTime;

public record ObtenerPymeDTO(
        String id,
        Aseguradora aseguradora,
        String nombre,
        String cedula,
        String correo,
        String telefono,
        String direccion,
        LocalDateTime fechaNacimiento,
        float valorMercancia,
        float valorMaquinaria,
        float valorComercial,
        float valorElectrico,
        float valorMuebles,
        TipoInmueble tipo
) {}
