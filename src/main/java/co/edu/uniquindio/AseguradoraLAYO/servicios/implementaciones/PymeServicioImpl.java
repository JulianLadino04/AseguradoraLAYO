package co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones;

import co.edu.uniquindio.AseguradoraLAYO.dto.PymeDTOs.CrearCotizacionPymeDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.PymeDTOs.ObtenerPymeDTO;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionPyme;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.PymeRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.PymeServicio;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.AseguradoraLAYO.dto.EmailDTOs.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PymeServicioImpl implements PymeServicio {

    private final PymeRepo pymeRepo;
    private final EmailServicio emailServicio;

    @Autowired
    public PymeServicioImpl(PymeRepo pymeRepo, EmailServicio emailServicio) {
        this.pymeRepo = pymeRepo;
        this.emailServicio = emailServicio;
    }

    @Override
    public void crearCotizacionPyme(CrearCotizacionPymeDTO pyme) throws Exception {
        // Verificar si ya existe una cotización con la misma cédula o correo
        if (pymeRepo.buscarPorCedula(pyme.cedula()).isPresent()) {
            throw new Exception("Ya existe una cotización con esta cédula.");
        }

        if (pymeRepo.buscarPorCorreo(pyme.correo()).isPresent()) {
            throw new Exception("Ya existe una cotización con este correo.");
        }

        // Crear una nueva cotización de Pyme
        CotizacionPyme nuevaCotizacion = CotizacionPyme.builder()
                .aseguradora(pyme.aseguradora())
                .nombre(pyme.nombre())
                .cedula(pyme.cedula())
                .correo(pyme.correo())
                .telefono(pyme.telefono())
                .direccion(pyme.direccion())
                .fechaNacimiento(pyme.fechaNacimiento())
                .valorMercancia(pyme.valorMercancia())
                .valorMaquinaria(pyme.valorMaquinaria())
                .valorComercial(pyme.valorComercial())
                .valorElectrico(pyme.valorElectrico())
                .valorMuebles(pyme.valorMuebles())
                .tipo(pyme.tipo())
                .build();

        // Guardar la cotización en la base de datos
        pymeRepo.save(nuevaCotizacion);

        // Enviar correo de confirmación
        emailServicio.enviarCorreo(new EmailDTO(
                "Cotización Pyme Creada",
                "Su cotización para Pyme ha sido registrada exitosamente.",
                pyme.correo()
        ));

        System.out.println("Cotización Pyme creada y correo enviado a: " + pyme.correo());
    }

    @Override
    public void eliminarCotizacionPyme(String id) throws Exception {
        // Buscar la cotización por id
        var cotizacionOpt = pymeRepo.findById(id);
        if (cotizacionOpt.isEmpty()) {
            throw new Exception("No se encontró una cotización con el id proporcionado.");
        }

        // Eliminar la cotización de la base de datos
        pymeRepo.delete(cotizacionOpt.get());

        System.out.println("Cotización Pyme con id " + id + " eliminada.");
    }

    @Override
    public List<ObtenerPymeDTO> listarPymes() throws Exception {
        List<CotizacionPyme> cotizaciones = pymeRepo.findAll(); // Obtener todas las cotizaciones

        if (cotizaciones.isEmpty()) {
            throw new Exception("No hay cotizaciones de Pyme registradas.");
        }

        // Convertir la lista de CotizacionPyme a una lista de DTOs
        return cotizaciones.stream()
                .map(cotizacion -> new ObtenerPymeDTO(
                        cotizacion.getId(),
                        cotizacion.getAseguradora(),
                        cotizacion.getNombre(),  // Ajusta si es necesario corregir el nombre del campo
                        cotizacion.getCedula(),
                        cotizacion.getCorreo(),
                        cotizacion.getTelefono(),
                        cotizacion.getDireccion(),
                        cotizacion.getFechaNacimiento(),
                        cotizacion.getValorMercancia(),
                        cotizacion.getValorMaquinaria(),
                        cotizacion.getValorComercial(),
                        cotizacion.getValorElectrico(),
                        cotizacion.getValorMuebles(),
                        cotizacion.getTipo()
                ))
                .collect(Collectors.toList());
    }
}
