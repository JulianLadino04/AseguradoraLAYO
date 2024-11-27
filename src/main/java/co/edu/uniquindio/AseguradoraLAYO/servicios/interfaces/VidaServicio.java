package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;

import co.edu.uniquindio.AseguradoraLAYO.dto.VidaDTOs.CrearCotizacionVidaDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.VidaDTOs.ObtenerVidaDTO;

import java.util.List;

public interface VidaServicio {
    void crearCotizacionVida(CrearCotizacionVidaDTO vida) throws Exception;
    void eliminarCotizacionVida(String cedula) throws Exception;
    List<ObtenerVidaDTO> listarVida() throws Exception;
}
