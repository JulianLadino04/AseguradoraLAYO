package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;

import co.edu.uniquindio.AseguradoraLAYO.dto.ResponsabilidadCivilDTOs.CrearCotizacionResponsabilidadCivilDTO;

public interface ResponsabilidadCivilServicio {
    void crearCotizacionResponsabilidadCivil(CrearCotizacionResponsabilidadCivilDTO responsabilidadCivil) throws Exception;
    void eliminarCotizacionResponsabilidadCivil(String cedula) throws Exception;
}
