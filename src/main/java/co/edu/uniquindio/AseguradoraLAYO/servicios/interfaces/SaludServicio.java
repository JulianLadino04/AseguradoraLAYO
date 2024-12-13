package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;

import co.edu.uniquindio.AseguradoraLAYO.dto.SaludDTOs.CrearCotizacionSaludDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.SaludDTOs.ObtenerSaludDTO;

import java.util.List;

public interface SaludServicio {
    void crearCotizacionSalud(CrearCotizacionSaludDTO salud) throws Exception;
    void eliminarCotizacionSalud(String id) throws Exception;
    List<ObtenerSaludDTO> listarSalud() throws Exception;

}
