package co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones;

import co.edu.uniquindio.AseguradoraLAYO.dto.ProteccionCreditoDTOs.CrearCotizacionProteccionCreditoDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.ProteccionCreditoDTOs.ObtenerProteccionCreditoDTO;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionProteccionCredito;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.ProteccionCreditoRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.ProteccionCreditoServicio;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.AseguradoraLAYO.dto.EmailDTOs.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProteccionCreditoServicioImpl implements ProteccionCreditoServicio {

    private final ProteccionCreditoRepo proteccionCreditoRepo;
    private final EmailServicio emailServicio;

    @Autowired
    public ProteccionCreditoServicioImpl(ProteccionCreditoRepo proteccionCreditoRepo, EmailServicio emailServicio) {
        this.proteccionCreditoRepo = proteccionCreditoRepo;
        this.emailServicio = emailServicio;
    }

    @Override
    public void crearCotizacionProteccionCredito(CrearCotizacionProteccionCreditoDTO proteccionCredito) throws Exception {
        // Verificar si ya existe una cotización con la misma cédula o correo
        if (proteccionCreditoRepo.buscarPorCedula(proteccionCredito.cedula()).isPresent()) {
            throw new Exception("Ya existe una cotización con esta cédula.");
        }

        if (proteccionCreditoRepo.buscarPorCorreo(proteccionCredito.correo()).isPresent()) {
            throw new Exception("Ya existe una cotización con este correo.");
        }

        // Crear una nueva cotización de protección de crédito
        CotizacionProteccionCredito nuevaCotizacion = CotizacionProteccionCredito.builder()
                .aseguradora(proteccionCredito.aseguradora())
                .nommbre(proteccionCredito.nombre())
                .cedula(proteccionCredito.cedula())
                .correo(proteccionCredito.correo())
                .telefono(proteccionCredito.telefono())
                .direccion(proteccionCredito.direccion())
                .fechaNacimiento(proteccionCredito.fechaNacimiento())
                .valorDeuda(proteccionCredito.valorDeuda())
                .build();

        // Guardar la cotización en la base de datos
        proteccionCreditoRepo.save(nuevaCotizacion);

        // Enviar correo de confirmación
        emailServicio.enviarCorreo(new EmailDTO(
                "Cotización de Protección de Crédito Creada",
                "Su cotización para protección de crédito ha sido registrada exitosamente.",
                proteccionCredito.correo()
        ));

        System.out.println("Cotización de protección de crédito creada y correo enviado a: " + proteccionCredito.correo());
    }

    @Override
    public void eliminarCotizacionProteccionCredito(String cedula) throws Exception {
        // Buscar la cotización por id
        var cotizacionOpt = proteccionCreditoRepo.buscarPorCedula(cedula);
        if (cotizacionOpt.isEmpty()) {
            throw new Exception("No se encontró una cotización con el id proporcionado.");
        }

        // Eliminar la cotización de la base de datos
        proteccionCreditoRepo.delete(cotizacionOpt.get());

        System.out.println("Cotización de protección de crédito con id " + cedula + " eliminada.");
    }

    @Override
    public List<ObtenerProteccionCreditoDTO> listarProteccionCreditos() throws Exception {
        List<CotizacionProteccionCredito> cotizaciones = proteccionCreditoRepo.findAll(); // Obtener todas las cotizaciones

        if (cotizaciones.isEmpty()) {
            throw new Exception("No hay cotizaciones de protección de crédito registradas.");
        }

        // Convertir la lista de CotizacionProteccionCredito a una lista de DTOs
        return cotizaciones.stream()
                .map(cotizacion -> new ObtenerProteccionCreditoDTO(
                        cotizacion.getId(),
                        cotizacion.getAseguradora(),
                        cotizacion.getNommbre(),  // Ajustar si el nombre del campo en la base de datos es incorrecto
                        cotizacion.getCedula(),
                        cotizacion.getCorreo(),
                        cotizacion.getTelefono(),
                        cotizacion.getDireccion(),
                        cotizacion.getFechaNacimiento(),
                        cotizacion.getValorDeuda()
                ))
                .collect(Collectors.toList());
    }
}
