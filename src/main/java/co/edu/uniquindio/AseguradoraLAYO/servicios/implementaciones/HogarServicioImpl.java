package co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones;

import co.edu.uniquindio.AseguradoraLAYO.dto.HogarDTOs.CrearCotizacionHogarDTO;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionHogar;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.HogarRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.HogarServicio;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.AseguradoraLAYO.dto.EmailDTOs.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HogarServicioImpl implements HogarServicio {

    private final HogarRepo hogarRepo;
    private final EmailServicio emailServicio;

    @Autowired
    public HogarServicioImpl(HogarRepo hogarRepo, EmailServicio emailServicio) {
        this.hogarRepo = hogarRepo;
        this.emailServicio = emailServicio;
    }

    @Override
    public void crearCotizacionHogar(CrearCotizacionHogarDTO hogar) throws Exception {
        // Verificar si ya existe una cotización con la misma cédula o correo
        if (hogarRepo.buscarPorCedula(hogar.cedula()).isPresent()) {
            throw new Exception("Ya existe una cotización con esta cédula.");
        }

        if (hogarRepo.buscarPorCorreo(hogar.correo()).isPresent()) {
            throw new Exception("Ya existe una cotización con este correo.");
        }

        // Crear una nueva cotización de hogar
        CotizacionHogar nuevaCotizacion = CotizacionHogar.builder()
                .aseguradora(hogar.aseguradora())
                .nommbre(hogar.nombre())
                .cedula(hogar.cedula())
                .correo(hogar.correo())
                .telefono(hogar.telefono())
                .direccion(hogar.direccion())
                .fechaNacimiento(hogar.fechaNacimiento())
                .valorComercial(hogar.valorComercial())
                .valorElectrico(hogar.valorElectrico())
                .valorMuebles(hogar.valorMuebles())
                .build();

        // Guardar la cotización en la base de datos
        hogarRepo.save(nuevaCotizacion);

        // Enviar correo de confirmación
        emailServicio.enviarCorreo(new EmailDTO(
                "Cotización de Hogar Creada",
                "Su cotización para hogar ha sido registrada exitosamente.",
                hogar.correo()
        ));

        System.out.println("Cotización de hogar creada y correo enviado a: " + hogar.correo());
    }

    @Override
    public void eliminarCotizacionHogar(String cedula) throws Exception {
        // Buscar la cotización por id
        var cotizacionOpt = hogarRepo.buscarPorCedula(cedula);
        if (cotizacionOpt.isEmpty()) {
            throw new Exception("No se encontró una cotización con el id proporcionado.");
        }

        // Eliminar la cotización de la base de datos
        hogarRepo.delete(cotizacionOpt.get());

        System.out.println("Cotización de hogar con id " + cedula + " eliminada.");
    }
}
