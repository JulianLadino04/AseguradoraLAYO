package co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones;

import co.edu.uniquindio.AseguradoraLAYO.dto.VidaDTOs.CrearCotizacionVidaDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.VidaDTOs.ObtenerVidaDTO;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionVida;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.VidaRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.VidaServicio;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.AseguradoraLAYO.dto.EmailDTOs.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VidaServicioImpl implements VidaServicio {

    private final VidaRepo vidaRepo;
    private final EmailServicio emailServicio;

    @Autowired
    public VidaServicioImpl(VidaRepo vidaRepo, EmailServicio emailServicio) {
        this.vidaRepo = vidaRepo;
        this.emailServicio = emailServicio;
    }

    @Override
    public void crearCotizacionVida(CrearCotizacionVidaDTO vida) throws Exception {
        // Verificar si ya existe una cotización con la misma cédula o correo
        if (vidaRepo.buscarPorCedula(vida.cedula()).isPresent()) {
            throw new Exception("Ya existe una cotización con esta cédula.");
        }

        if (vidaRepo.buscarPorCorreo(vida.correo()).isPresent()) {
            throw new Exception("Ya existe una cotización con este correo.");
        }

        // Crear una nueva cotización de Vida
        CotizacionVida nuevaCotizacion = CotizacionVida.builder()
                .aseguradora(vida.aseguradora())
                .nommbre(vida.nombre())
                .cedula(vida.cedula())
                .correo(vida.correo())
                .telefono(vida.telefono())
                .direccion(vida.direccion())
                .ocupacion(vida.ocupacion())
                .fechaNacimiento(vida.fechaNacimiento())
                .build();

        // Guardar la cotización en la base de datos
        vidaRepo.save(nuevaCotizacion);

        // Enviar correo de confirmación
        emailServicio.enviarCorreo(new EmailDTO(
                "Cotización Vida Creada",
                "Su cotización de Vida ha sido registrada exitosamente.",
                vida.correo()
        ));

        System.out.println("Cotización Vida creada y correo enviado a: " + vida.correo());
    }

    @Override
    public void eliminarCotizacionVida(String cedula) throws Exception {
        // Buscar la cotización por id
        var cotizacionOpt = vidaRepo.buscarPorCedula(cedula);
        if (cotizacionOpt.isEmpty()) {
            throw new Exception("No se encontró una cotización con el id proporcionado.");
        }

        // Eliminar la cotización de la base de datos
        vidaRepo.delete(cotizacionOpt.get());

        System.out.println("Cotización Vida con id " + cedula + " eliminada.");
    }

    @Override
    public List<ObtenerVidaDTO> listarVida() throws Exception {
        List<CotizacionVida> cotizaciones = vidaRepo.findAll(); // Obtener todas las cotizaciones

        if (cotizaciones.isEmpty()) {
            throw new Exception("No hay cotizaciones de vida registradas.");
        }

        // Convertir la lista de CotizacionVida a una lista de DTOs
        return cotizaciones.stream()
                .map(cotizacion -> new ObtenerVidaDTO(
                        cotizacion.getId(),
                        cotizacion.getAseguradora(),
                        cotizacion.getNommbre(),  // Ajusta si es necesario corregir el nombre del campo
                        cotizacion.getCedula(),
                        cotizacion.getCorreo(),
                        cotizacion.getTelefono(),
                        cotizacion.getDireccion(),
                        cotizacion.getOcupacion(),
                        cotizacion.getFechaNacimiento()
                ))
                .collect(Collectors.toList());
    }
}
