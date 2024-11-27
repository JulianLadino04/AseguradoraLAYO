package co.edu.uniquindio.AseguradoraLAYO;

import co.edu.uniquindio.AseguradoraLAYO.dto.AutosDTOs.CrearCotizacionAutoDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.AutosDTOs.ObtenerAutosDTO;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionAutos;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.TipoVehiculo;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.AutosRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones.AutosServicioImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AutosTest {

    @Autowired
    private AutosServicioImpl autosServicio;

    @Autowired
    private AutosRepo autosRepo;

    @BeforeEach
    public void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Cargar el archivo JSON desde el directorio de recursos
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("autos.json");
        assertNotNull(inputStream, "Archivo autos.json no encontrado");

        List<CotizacionAutos> cotizaciones = objectMapper.readValue(inputStream, new TypeReference<List<CotizacionAutos>>() {});

        // Limpiar el repositorio antes de agregar nuevos registros
        autosRepo.deleteAll();
        autosRepo.saveAll(cotizaciones);
    }

    @Test
    public void testListarAutos() throws Exception {
        List<ObtenerAutosDTO> cotizaciones = autosServicio.listarAutos();
        assertFalse(cotizaciones.isEmpty(), "La lista de cotizaciones no debe estar vacía");
    }

    @Test
    public void testCrearCotizacionAuto() throws Exception {
        CrearCotizacionAutoDTO crearCotizacionAutoDTO = new CrearCotizacionAutoDTO(
                Aseguradora.SURA,"DEF456", "Ana Gomez", "123456789", "ana@gmail.com", "Carrera 45 #12-34", "1234567890", "Bogota", TipoVehiculo.PARTICULAR, LocalDateTime.of(1990, 5, 20, 10, 30)

        );

        // Ejecutar el método de crear cotización
        autosServicio.crearCotizacionAutos(crearCotizacionAutoDTO);

        // Verificar que la cotización fue guardada en el repositorio
        CotizacionAutos cotizacionGuardada = autosRepo.buscarPorPlaca("DEF456")
                .orElseThrow(() -> new Exception("Cotización no encontrada"));
        assertEquals("Ana Gomez", cotizacionGuardada.getNombre(), "El nombre del titular debe coincidir");
    }

    @Test
    public void testEliminarCotizacionAuto() throws Exception {
        // Buscar una cotización existente
        CotizacionAutos cotizacionGuardada = autosRepo.buscarPorPlaca("ABC123")
                .orElseThrow(() -> new Exception("Cotización no encontrada"));

        // Ejecutar el método para eliminar la cotización
        autosServicio.eliminarCotizacionAutos(cotizacionGuardada.getNumeroPlaca());

        // Verificar que la cotización ha sido eliminada
        assertFalse(autosRepo.buscarPorPlaca("ABC123").isPresent(), "La cotización debe haber sido eliminada");
    }

    @Test
    public void testEliminarCotizacionNoExistente() throws Exception {
        // Intentar eliminar una cotización con placa inexistente
        String placaNoExistente = "XYZ999";
        Exception exception = assertThrows(Exception.class, () -> autosServicio.eliminarCotizacionAutos(placaNoExistente));
        assertEquals("No se encontró una cotización con la placa proporcionada", exception.getMessage());
    }
}

