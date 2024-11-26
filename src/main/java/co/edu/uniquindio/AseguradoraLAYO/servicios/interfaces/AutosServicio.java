package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;

import co.edu.uniquindio.AseguradoraLAYO.dto.AutosDTOs.CrearCotizacionAutosDTO;

public interface AutosServicio {
    void crearCotizacionAutos(CrearCotizacionAutosDTO autos) throws Exception;
    void eliminarCotizacionAutos(String placa) throws Exception;
}

