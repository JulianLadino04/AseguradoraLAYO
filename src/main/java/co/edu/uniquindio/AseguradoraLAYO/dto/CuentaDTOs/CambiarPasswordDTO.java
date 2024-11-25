package co.edu.uniquindio.AseguradoraLAYO.dto.CuentaDTOs;

public record CambiarPasswordDTO(
        String codigoVerificacion,
        String passwordNueva,
        String correo
) {
}
