package co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones;

import co.edu.uniquindio.AseguradoraLAYO.dto.SaludDTOs.CrearCotizacionSaludDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.SaludDTOs.ObtenerSaludDTO;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionSalud;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.SaludRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.SaludServicio;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.AseguradoraLAYO.dto.EmailDTOs.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SaludServicioImpl implements SaludServicio {

    private final SaludRepo saludRepo;
    private final EmailServicio emailServicio;

    @Autowired
    public SaludServicioImpl(SaludRepo saludRepo, EmailServicio emailServicio) {
        this.saludRepo = saludRepo;
        this.emailServicio = emailServicio;
    }

    @Override
    public void crearCotizacionSalud(CrearCotizacionSaludDTO salud) throws Exception {
        // Verificar si ya existe una cotización con la misma cédula o correo
        if (saludRepo.buscarPorCedula(salud.cedula()).isPresent()) {
            throw new Exception("Ya existe una cotización con esta cédula.");
        }

        if (saludRepo.buscarPorCorreo(salud.correo()).isPresent()) {
            throw new Exception("Ya existe una cotización con este correo.");
        }

        // Crear una nueva cotización de Salud
        CotizacionSalud nuevaCotizacion = CotizacionSalud.builder()
                .aseguradora(salud.aseguradora())
                .nommbre(salud.nombre())
                .cedula(salud.cedula())
                .correo(salud.correo())
                .telefono(salud.telefono())
                .direccion(salud.direccion())
                .fechaNacimiento(salud.fechaNacimiento())
                .build();

        // Guardar la cotización en la base de datos
        saludRepo.save(nuevaCotizacion);

        // Enviar correo de confirmación
        emailServicio.enviarCorreo(new EmailDTO(
                "Cotización Salud Creada",
                "Su cotización de Salud ha sido registrada exitosamente.",
                salud.correo()
        ));

        System.out.println("Cotización Salud creada y correo enviado a: " + salud.correo());
    }

    @Override
    public void eliminarCotizacionSalud(String id) throws Exception {
        // Buscar la cotización por id
        var cotizacionOpt = saludRepo.findById(id);
        if (cotizacionOpt.isEmpty()) {
            throw new Exception("No se encontró una cotización con el id proporcionado.");
        }

        // Eliminar la cotización de la base de datos
        saludRepo.delete(cotizacionOpt.get());

        System.out.println("Cotización Salud con id " + id + " eliminada.");
    }

    @Override
    public List<ObtenerSaludDTO> listarSalud() throws Exception {
        List<CotizacionSalud> cotizaciones = saludRepo.findAll(); // Obtener todas las cotizaciones

        if (cotizaciones.isEmpty()) {
            throw new Exception("No hay cotizaciones de salud registradas.");
        }

        // Convertir la lista de CotizacionSalud a una lista de DTOs
        return cotizaciones.stream()
                .map(cotizacion -> new ObtenerSaludDTO(
                        cotizacion.getId(),
                        cotizacion.getAseguradora(),
                        cotizacion.getNommbre(),  // Ajusta si es necesario corregir el nombre del campo
                        cotizacion.getCedula(),
                        cotizacion.getCorreo(),
                        cotizacion.getTelefono(),
                        cotizacion.getDireccion(),
                        cotizacion.getFechaNacimiento()
                ))
                .collect(Collectors.toList());
    }
}
