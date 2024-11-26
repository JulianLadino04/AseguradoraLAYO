package co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones;

import co.edu.uniquindio.AseguradoraLAYO.dto.AutosDTOs.CrearCotizacionAutosDTO;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionAutos;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.AutosRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.AutosServicio;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.AseguradoraLAYO.dto.EmailDTOs.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AutosServicioImpl implements AutosServicio {

    private final AutosRepo autosRepo;
    private final EmailServicio emailServicio;

    @Autowired
    public AutosServicioImpl(AutosRepo autosRepo, EmailServicio emailServicio) {
        this.autosRepo = autosRepo;
        this.emailServicio = emailServicio;
    }

    @Override
    public void crearCotizacionAutos(CrearCotizacionAutosDTO autos) throws Exception {
        // Verificar si la placa ya existe
        if (autosRepo.buscarPorPlaca(autos.placa()).isPresent()) {
            throw new Exception("Ya existe una cotización con esta placa");
        }

        // Crear nueva instancia de CotizacionAutos
        CotizacionAutos nuevaCotizacion = CotizacionAutos.builder()
                .aseguradora(autos.aseguradora()) // Suponiendo que se pasa como String en el DTO y se convierte a Enum aquí
                .numeroPlaca(autos.placa())
                .nombre(autos.nombre())
                .cedula(autos.cedula())
                .email(autos.correo())
                .telefono(autos.telefono())
                .ciudadCirculacion(autos.ciudadCirculacion())
                .tipo(autos.tipo()) // Enum también
                .fechaNacimiento(autos.fechaNacimiento())
                .build();

        // Guardar en la base de datos
        autosRepo.save(nuevaCotizacion);

        // Enviar correo de confirmación
        emailServicio.enviarCorreo(new EmailDTO(
                "Cotización de Autos Creada",
                "Su cotización para el vehículo con placa " + autos.placa() + " ha sido registrada exitosamente.",
                autos.correo()
        ));

        System.out.println("Cotización de auto creada y correo enviado a: " + autos.correo());
    }

    @Override
    public void eliminarCotizacionAutos(String placa) throws Exception {
        // Buscar la cotización por placa
        var cotizacionOpt = autosRepo.buscarPorPlaca(placa);
        if (cotizacionOpt.isEmpty()) {
            throw new Exception("No se encontró una cotización con la placa proporcionada");
        }

        // Eliminar de la base de datos
        autosRepo.delete(cotizacionOpt.get());

        System.out.println("Cotización de auto con placa " + placa + " eliminada.");
    }
}
