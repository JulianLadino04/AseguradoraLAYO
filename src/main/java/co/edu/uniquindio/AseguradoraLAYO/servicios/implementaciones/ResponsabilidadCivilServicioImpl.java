package co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones;

import co.edu.uniquindio.AseguradoraLAYO.dto.ResponsabilidadCivilDTOs.CrearCotizacionResponsabilidadCivilDTO;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionResponsabilidadCivil;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.ResponsabilidadCivilRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.ResponsabilidadCivilServicio;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.AseguradoraLAYO.dto.EmailDTOs.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResponsabilidadCivilServicioImpl implements ResponsabilidadCivilServicio {

    private final ResponsabilidadCivilRepo responsabilidadCivilRepo;
    private final EmailServicio emailServicio;

    @Autowired
    public ResponsabilidadCivilServicioImpl(ResponsabilidadCivilRepo responsabilidadCivilRepo, EmailServicio emailServicio) {
        this.responsabilidadCivilRepo = responsabilidadCivilRepo;
        this.emailServicio = emailServicio;
    }

    @Override
    public void crearCotizacionResponsabilidadCivil(CrearCotizacionResponsabilidadCivilDTO responsabilidadCivil) throws Exception {
        // Verificar si ya existe una cotización con la misma cédula o correo
        if (responsabilidadCivilRepo.buscarPorCedula(responsabilidadCivil.cedula()).isPresent()) {
            throw new Exception("Ya existe una cotización con esta cédula.");
        }

        if (responsabilidadCivilRepo.buscarPorCorreo(responsabilidadCivil.correo()).isPresent()) {
            throw new Exception("Ya existe una cotización con este correo.");
        }

        // Crear una nueva cotización de Responsabilidad Civil
        CotizacionResponsabilidadCivil nuevaCotizacion = CotizacionResponsabilidadCivil.builder()
                .aseguradora(responsabilidadCivil.aseguradora())
                .nommbre(responsabilidadCivil.nombre())
                .cedula(responsabilidadCivil.cedula())
                .correo(responsabilidadCivil.correo())
                .telefono(responsabilidadCivil.telefono())
                .direccion(responsabilidadCivil.direccion())
                .fechaNacimiento(responsabilidadCivil.fechaNacimiento())
                .ocupacion(responsabilidadCivil.ocupacion())
                .ciudad(responsabilidadCivil.ciudad())
                .build();

        // Guardar la cotización en la base de datos
        responsabilidadCivilRepo.save(nuevaCotizacion);

        // Enviar correo de confirmación
        emailServicio.enviarCorreo(new EmailDTO(
                "Cotización Responsabilidad Civil Creada",
                "Su cotización de Responsabilidad Civil ha sido registrada exitosamente.",
                responsabilidadCivil.correo()
        ));

        System.out.println("Cotización Responsabilidad Civil creada y correo enviado a: " + responsabilidadCivil.correo());
    }

    @Override
    public void eliminarCotizacionResponsabilidadCivil(String cedula) throws Exception {
        // Buscar la cotización por id
        var cotizacionOpt = responsabilidadCivilRepo.buscarPorCedula(cedula);
        if (cotizacionOpt.isEmpty()) {
            throw new Exception("No se encontró una cotización con el id proporcionado.");
        }

        // Eliminar la cotización de la base de datos
        responsabilidadCivilRepo.delete(cotizacionOpt.get());

        System.out.println("Cotización Responsabilidad Civil con id " + cedula + " eliminada.");
    }
}
