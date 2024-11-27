package co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones;

import co.edu.uniquindio.AseguradoraLAYO.dto.SoatDTOs.CrearSoatDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.SoatDTOs.ObtenerSoatDTO;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.SOAT;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.SoatRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.SoatServicio;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.AseguradoraLAYO.dto.EmailDTOs.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SoatServicioImpl implements SoatServicio {

    private final SoatRepo soatRepo;
    private final EmailServicio emailServicio;

    @Autowired
    public SoatServicioImpl(SoatRepo soatRepo, EmailServicio emailServicio) {
        this.soatRepo = soatRepo;
        this.emailServicio = emailServicio;
    }

    @Override
    public void crearSoat(CrearSoatDTO soat) throws Exception {

        // Crear nueva instancia de Soat
        SOAT nuevoSoat = new SOAT();
        nuevoSoat.setNumeroPlaca(soat.placa());
        nuevoSoat.setNombre(soat.nombre());
        nuevoSoat.setCedula(soat.cedula());
        nuevoSoat.setTelefono(soat.telefono());
        nuevoSoat.setDireccion(soat.direccion());
        nuevoSoat.setCorreo(soat.correo());

        // Guardar en la base de datos
        soatRepo.save(nuevoSoat);

        // Enviar correo electrónico de confirmación
        emailServicio.enviarCorreo(new EmailDTO(
                "Registro de SOAT exitoso",
                "Su SOAT ha sido registrado exitosamente." ,
                soat.correo()
        ));

        System.out.println("SOAT creado y correo de confirmación enviado a: " + soat.correo());
    }

    @Override
    public void eliminarSoat(String placa) throws Exception {
        // Verificar si el SOAT existe en la base de datos
        if (!existePlaca(placa)) {
            throw new Exception("No se encontró un SOAT con la placa: " + placa);
        }

        // Eliminar el SOAT de la base de datos
        soatRepo.eliminarPorPlaca(placa);
        System.out.println("SOAT con placa " + placa + " eliminado exitosamente.");
    }

    // Método auxiliar para verificar si una placa ya existe
    private boolean existePlaca(String placa) {
        return soatRepo.buscarSoatPorPlaca(placa).isPresent(); // Simulación de consulta a la base de datos
    }

    @Override
    public List<ObtenerSoatDTO> listarSoat() throws Exception {
        List<SOAT> soats = soatRepo.findAll();  // Obtener todas las cotizaciones de SOAT

        if (soats.isEmpty()) {
            throw new Exception("No hay cotizaciones de SOAT registradas.");
        }

        // Convertir la lista de SOAT a una lista de DTOs
        return soats.stream()
                .map(soat -> new ObtenerSoatDTO(
                        soat.getId(),
                        soat.getNumeroPlaca(),
                        soat.getNombre(),
                        soat.getCedula(),
                        soat.getCorreo(),
                        soat.getTelefono(),
                        soat.getDireccion(),
                        soat.getAseguradora(),
                        soat.getTipo()
                ))
                .collect(Collectors.toList());
    }
}

