package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;

import co.edu.uniquindio.AseguradoraLAYO.dto.ResponsabilidadCivilDTOs.CrearCotizacionResponsabilidadCivilDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.ResponsabilidadCivilDTOs.ObtenerResponsabilidadCivilDTO;

import java.util.List;

public interface ResponsabilidadCivilServicio {
    void crearCotizacionResponsabilidadCivil(CrearCotizacionResponsabilidadCivilDTO responsabilidadCivil) throws Exception;
    void eliminarCotizacionResponsabilidadCivil(String cedula) throws Exception;
    List<ObtenerResponsabilidadCivilDTO> listarResponsabilidadCivil() throws Exception;
}
