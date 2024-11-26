package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;

import co.edu.uniquindio.AseguradoraLAYO.dto.SoatDTOs.CrearSoatDTO;

public interface SoatServicio {
    void crearSoat(CrearSoatDTO soat) throws Exception;
    void eliminarSoat(String placa) throws Exception;

}
