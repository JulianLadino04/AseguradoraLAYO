package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;

import co.edu.uniquindio.AseguradoraLAYO.dto.VidaDTOS.CrearCotizacionVidaDTO;

public interface VidaServicio {
    void crearCotizacionVida(CrearCotizacionVidaDTO vida) throws Exception;
    void eliminarCotizacionVida(String cedula) throws Exception;
}
