package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;

import co.edu.uniquindio.AseguradoraLAYO.dto.PymeDTOs.CrearCotizacionPymeDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.PymeDTOs.ObtenerPymeDTO;

import java.util.List;

public interface PymeServicio {
    void crearCotizacionPyme(CrearCotizacionPymeDTO pyme) throws Exception;
    void eliminarCotizacionPyme(String id) throws Exception;
    List<ObtenerPymeDTO> listarPymes() throws Exception;
}
