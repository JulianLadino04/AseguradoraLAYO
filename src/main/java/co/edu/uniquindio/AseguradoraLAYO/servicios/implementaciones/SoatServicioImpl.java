package co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones;

import co.edu.uniquindio.AseguradoraLAYO.dto.SoatDTOs.CrearSoatDTO;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.SOAT;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.SoatRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.SoatServicio;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.AseguradoraLAYO.dto.EmailDTOs.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // Método auxiliar para verificar si una placa ya existe
    private boolean existePlaca(String placa) {
        return soatRepo.buscarSoatPorPlaca(placa).isPresent(); // Simulación de consulta a la base de datos
    }

}

