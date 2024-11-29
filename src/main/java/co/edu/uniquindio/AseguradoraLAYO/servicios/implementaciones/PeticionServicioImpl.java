package co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones;

import co.edu.uniquindio.AseguradoraLAYO.dto.PeticionDTOs.CrearPeticionDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.PeticionDTOs.ObtenerPeticionDTO;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.Peticion;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.PeticionRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.PeticionServicio;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.AseguradoraLAYO.dto.EmailDTOs.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PeticionServicioImpl implements PeticionServicio {

    private final PeticionRepo peticionRepo;
    private final EmailServicio emailServicio;

    @Autowired
    public PeticionServicioImpl(PeticionRepo peticionRepo, EmailServicio emailServicio) {
        this.peticionRepo = peticionRepo;
        this.emailServicio = emailServicio;
    }

    @Override
    public void crearPeticion(CrearPeticionDTO peticionDTO) throws Exception {
        // Validación para evitar duplicados usando la cédula
        if (peticionRepo.buscarPorCedula(peticionDTO.cedula()).isPresent()) {
            throw new Exception("Ya existe una petición con esta cédula");
        }

        // Crear nueva instancia de Peticion a partir del DTO
        Peticion nuevaPeticion = Peticion.builder()
                .cedula(peticionDTO.cedula())
                .nombre(peticionDTO.nombre())
                .correo(peticionDTO.correo())
                .telefono(peticionDTO.telefono())
                .asegurar(peticionDTO.asegurar())
                .mensaje(peticionDTO.mensaje())
                .build();

        // Guardar en la base de datos
        peticionRepo.save(nuevaPeticion);

        // Enviar correo de confirmación
        emailServicio.enviarCorreo(new EmailDTO(
                "Petición Registrada",
                "Su petición ha sido registrada exitosamente. Nos pondremos en contacto con usted pronto.",
                peticionDTO.correo()
        ));

        System.out.println("Petición creada y correo enviado a: " + peticionDTO.correo());
    }

    @Override
    public void eliminarPeticion(String cedula) throws Exception {
        // Buscar la petición por cédula
        var peticionOpt = peticionRepo.buscarPorCedula(cedula);
        if (peticionOpt.isEmpty()) {
            throw new Exception("No se encontró una petición con la cédula proporcionada");
        }

        // Eliminar de la base de datos
        peticionRepo.delete(peticionOpt.get());

        System.out.println("Petición con cédula " + cedula + " eliminada.");
    }

    @Override
    public List<ObtenerPeticionDTO> listarPeticiones() throws Exception {
        List<Peticion> peticiones = peticionRepo.findAll();

        if (peticiones.isEmpty()) {
            throw new Exception("No hay peticiones registradas.");
        }

        // Convertir la lista de Peticion a DTOs
        return peticiones.stream()
                .map(peticion -> new ObtenerPeticionDTO(
                        peticion.getCedula(),
                        peticion.getNombre(),
                        peticion.getCorreo(),
                        peticion.getTelefono(),
                        peticion.getAsegurar(),
                        peticion.getMensaje()
                ))
                .collect(Collectors.toList());
    }
}
