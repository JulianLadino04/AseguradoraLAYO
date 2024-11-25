package co.edu.uniquindio.AseguradoraLAYO.dto.TokenDTOs;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}
