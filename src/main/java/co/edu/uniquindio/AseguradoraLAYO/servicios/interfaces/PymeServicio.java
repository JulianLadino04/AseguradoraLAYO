package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;

import co.edu.uniquindio.AseguradoraLAYO.dto.PymeDTOs.CrearCotizacionPymeDTO;

public interface PymeServicio {
    void crearCotizacionPyme(CrearCotizacionPymeDTO pyme) throws Exception;
    void eliminarCotizacionPyme(String cedula) throws Exception;
}
