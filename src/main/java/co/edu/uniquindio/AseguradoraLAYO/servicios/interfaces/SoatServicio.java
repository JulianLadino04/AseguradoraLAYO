package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;

import co.edu.uniquindio.AseguradoraLAYO.dto.SoatDTOs.CrearSoatDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.SoatDTOs.ObtenerSoatDTO;

import java.util.List;

public interface SoatServicio {
    void crearSoat(CrearSoatDTO soat) throws Exception;
    void eliminarSoat(String id) throws Exception;
    List<ObtenerSoatDTO> listarSoat() throws Exception;

}
