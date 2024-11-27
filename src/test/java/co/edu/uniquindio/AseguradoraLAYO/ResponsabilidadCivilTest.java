package co.edu.uniquindio.AseguradoraLAYO;

import co.edu.uniquindio.AseguradoraLAYO.dto.ResponsabilidadCivilDTOs.CrearCotizacionResponsabilidadCivilDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.ResponsabilidadCivilDTOs.ObtenerResponsabilidadCivilDTO;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionResponsabilidadCivil;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.ResponsabilidadCivilRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones.ResponsabilidadCivilServicioImpl;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ResponsabilidadCivilTest {

    @Autowired
    private ResponsabilidadCivilServicioImpl responsabilidadCivilServicio;

    @Autowired
    private ResponsabilidadCivilRepo responsabilidadCivilRepo;

    @BeforeEach
    public void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Cargar los datos desde el archivo JSON
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("responsabilidadCivil.json");
        assertNotNull(inputStream, "Archivo responsabilidadCivil.json no encontrado");

        List<CotizacionResponsabilidadCivil> cotizaciones = objectMapper.readValue(inputStream, new TypeReference<List<CotizacionResponsabilidadCivil>>() {});

        // Limpiar el repositorio antes de cada prueba
        responsabilidadCivilRepo.deleteAll();
        responsabilidadCivilRepo.saveAll(cotizaciones);
    }

    @Test
    public void testCrearCotizacionResponsabilidadCivil() throws Exception {
        CrearCotizacionResponsabilidadCivilDTO nuevaCotizacionDTO = new CrearCotizacionResponsabilidadCivilDTO(
                Aseguradora.BOLIVAR,
                "Juan Pérez",
                "123456789",
                "juan.perez@example.com",
                "3123456789",
                "Calle Ficticia 123",
                LocalDateTime.of(1985, 8, 15, 9, 0),
                "Ingeniero",
                "Medellín"
        );

        // Llamar al servicio para crear la cotización
        responsabilidadCivilServicio.crearCotizacionResponsabilidadCivil(nuevaCotizacionDTO);

        // Verificar que la cotización fue guardada en el repositorio
        Optional<CotizacionResponsabilidadCivil> cotizacionOpt = responsabilidadCivilRepo.buscarPorCedula("123456789");
        assertTrue(cotizacionOpt.isPresent(), "La cotización debe existir en la base de datos");
        assertEquals("Juan Pérez", cotizacionOpt.get().getNommbre(), "El nombre debe coincidir");
    }

    @Test
    public void testEliminarCotizacionResponsabilidadCivil() throws Exception {
        // Insertar una cotización antes de probar la eliminación
        CotizacionResponsabilidadCivil cotizacion = CotizacionResponsabilidadCivil.builder()
                .aseguradora(Aseguradora.SURA)
                .nommbre("Carlos Martínez")
                .cedula("987654321")
                .correo("carlos@example.com")
                .telefono("3145678901")
                .direccion("Carrera 25 #45-67")
                .fechaNacimiento(LocalDateTime.of(1992, 10, 10, 10, 0))
                .ocupacion("Abogado")
                .ciudad("Cali")
                .build();

        responsabilidadCivilRepo.save(cotizacion);

        // Eliminar la cotización
        responsabilidadCivilServicio.eliminarCotizacionResponsabilidadCivil("987654321");

        // Verificar que la cotización ha sido eliminada
        assertFalse(responsabilidadCivilRepo.buscarPorCedula("987654321").isPresent(), "La cotización debe haber sido eliminada");
    }

    @Test
    public void testListarResponsabilidadCivil() throws Exception {
        // Listar cotizaciones
        List<ObtenerResponsabilidadCivilDTO> lista = responsabilidadCivilServicio.listarResponsabilidadCivil();
        assertFalse(lista.isEmpty(), "La lista no debe estar vacía");
    }
}
