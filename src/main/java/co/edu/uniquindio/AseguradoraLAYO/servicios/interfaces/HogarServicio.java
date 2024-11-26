package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;

import co.edu.uniquindio.AseguradoraLAYO.dto.HogarDTOs.CrearCotizacionHogarDTO;

public interface HogarServicio {
    void crearCotizacionHogar(CrearCotizacionHogarDTO hogar) throws Exception;
    void eliminarCotizacionHogar(String cedula) throws Exception;
}
