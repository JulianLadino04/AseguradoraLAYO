package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;

import co.edu.uniquindio.AseguradoraLAYO.dto.AutosDTOs.CrearCotizacionAutoDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.AutosDTOs.ObtenerAutosDTO;

import java.util.List;

public interface AutosServicio {
    void crearCotizacionAutos(CrearCotizacionAutoDTO autos) throws Exception;

    void eliminarCotizacionAutos(String id) throws Exception;

    List<ObtenerAutosDTO> listarAutos() throws Exception;
}
