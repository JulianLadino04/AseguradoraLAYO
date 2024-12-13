package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;

import co.edu.uniquindio.AseguradoraLAYO.dto.ProteccionCreditoDTOs.CrearCotizacionProteccionCreditoDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.ProteccionCreditoDTOs.ObtenerProteccionCreditoDTO;

import java.util.List;

public interface ProteccionCreditoServicio {
    void crearCotizacionProteccionCredito(CrearCotizacionProteccionCreditoDTO proteccionCredito) throws Exception;
    void eliminarCotizacionProteccionCredito(String id) throws Exception;
    List<ObtenerProteccionCreditoDTO> listarProteccionCreditos() throws Exception;
}
