package co.edu.uniquindio.AseguradoraLAYO.dto.PeticionDTOs;

public record ObtenerPeticionDTO(
        String nombre,
        String cedula,
        String correo,
        String telefono,
        String asegurar,
        String mensaje
) {
}
