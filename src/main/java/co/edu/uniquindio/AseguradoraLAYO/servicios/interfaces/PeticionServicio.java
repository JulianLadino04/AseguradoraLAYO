package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;



import co.edu.uniquindio.AseguradoraLAYO.dto.PeticionDTOs.CrearPeticionDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.PeticionDTOs.ObtenerPeticionDTO;

import java.util.List;

public interface PeticionServicio {
    void crearPeticion(CrearPeticionDTO peticion) throws Exception;

    void eliminarPeticion(String id) throws Exception;

    List<ObtenerPeticionDTO> listarPeticiones() throws Exception;
}
