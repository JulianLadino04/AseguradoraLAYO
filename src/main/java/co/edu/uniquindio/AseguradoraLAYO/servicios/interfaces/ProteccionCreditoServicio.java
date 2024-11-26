package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;

import co.edu.uniquindio.AseguradoraLAYO.dto.ProteccionCreditoDTOs.CrearCotizacionProteccionCreditoDTO;

public interface ProteccionCreditoServicio {
    void crearCotizacionProteccionCredito(CrearCotizacionProteccionCreditoDTO proteccionCredito) throws Exception;
    void eliminarCotizacionProteccionCredito(String id) throws Exception;
}
