package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;

import co.edu.uniquindio.AseguradoraLAYO.dto.EmailDTOs.EmailDTO;
import org.springframework.scheduling.annotation.Async;

public interface EmailServicio {

    void enviarCorreo (EmailDTO emailDTO) throws Exception;

    @Async
    void enviarCorreoRecuperacion(String correo_destino) throws Exception;

    void enviarCorreoPrueba(EmailDTO emailDTO) throws Exception;

    void enviarCorreoRecuperacionPrueba(String correo_destino) throws Exception;
}
