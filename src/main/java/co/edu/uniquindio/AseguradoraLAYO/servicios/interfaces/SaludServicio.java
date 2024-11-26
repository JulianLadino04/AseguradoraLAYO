package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;

import co.edu.uniquindio.AseguradoraLAYO.dto.SaludDTOs.CrearCotizacionSaludDTO;

public interface SaludServicio {
    void crearCotizacionSalud(CrearCotizacionSaludDTO salud) throws Exception;
    void eliminarCotizacionSalud(String cedula) throws Exception;
}
