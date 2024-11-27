package co.edu.uniquindio.AseguradoraLAYO;

import co.edu.uniquindio.AseguradoraLAYO.dto.SaludDTOs.CrearCotizacionSaludDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.SaludDTOs.ObtenerSaludDTO;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionSalud;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.SaludRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones.SaludServicioImpl;
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
public class SaludTest {

    @Autowired
    private SaludServicioImpl saludServicio;

    @Autowired
    private SaludRepo saludRepo;

    @BeforeEach
    public void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Cargar los datos desde el archivo JSON
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("salud.json");
        assertNotNull(inputStream, "Archivo salud.json no encontrado");

        List<CotizacionSalud> cotizaciones = objectMapper.readValue(inputStream, new TypeReference<List<CotizacionSalud>>() {});

        // Limpiar el repositorio antes de cada prueba
        saludRepo.deleteAll();
        saludRepo.saveAll(cotizaciones);
    }

    @Test
    public void testCrearCotizacionSalud() throws Exception {
        CrearCotizacionSaludDTO nuevaCotizacionDTO = new CrearCotizacionSaludDTO(
                Aseguradora.BOLIVAR,
                "Juan Pérez",
                "123456789",
                "juan.perez@example.com",
                "3123456789",
                "Calle Ficticia 123",
                LocalDateTime.of(1985, 8, 15, 9, 0)
        );

        saludServicio.crearCotizacionSalud(nuevaCotizacionDTO);

        Optional<CotizacionSalud> cotizacionOpt = saludRepo.buscarPorCedula("123456789");
        assertTrue(cotizacionOpt.isPresent(), "La cotización debe existir en la base de datos");
        assertEquals("Juan Pérez", cotizacionOpt.get().getNommbre(), "El nombre debe coincidir");
    }

    @Test
    public void testEliminarCotizacionSalud() throws Exception {
        // Insertar una cotización antes de probar la eliminación
        CotizacionSalud cotizacion = CotizacionSalud.builder()
                .aseguradora(Aseguradora.SURA)
                .nommbre("Carlos Martínez")
                .cedula("987654321")
                .correo("carlos@example.com")
                .telefono("3145678901")
                .direccion("Carrera 25 #45-67")
                .fechaNacimiento(LocalDateTime.of(1992, 10, 10, 10, 0))
                .build();
        saludRepo.save(cotizacion);

        // Eliminar la cotización
        saludServicio.eliminarCotizacionSalud("987654321");

        assertFalse(saludRepo.buscarPorCedula("987654321").isPresent(), "La cotización debe haber sido eliminada");
    }

    @Test
    public void testListarSalud() throws Exception {
        // Listar cotizaciones
        List<ObtenerSaludDTO> lista = saludServicio.listarSalud();
        assertFalse(lista.isEmpty(), "La lista no debe estar vacía");
    }
}
