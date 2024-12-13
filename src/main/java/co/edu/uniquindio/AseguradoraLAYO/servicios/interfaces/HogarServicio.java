package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;

import co.edu.uniquindio.AseguradoraLAYO.dto.HogarDTOs.CrearCotizacionHogarDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.HogarDTOs.ObtenerHogarDTO;

import java.util.List;

public interface HogarServicio {
    void crearCotizacionHogar(CrearCotizacionHogarDTO hogar) throws Exception;
    void eliminarCotizacionHogar(String id) throws Exception;
    List<ObtenerHogarDTO> listarHogares() throws Exception;
}
