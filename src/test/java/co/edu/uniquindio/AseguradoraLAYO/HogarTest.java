package co.edu.uniquindio.AseguradoraLAYO;

import co.edu.uniquindio.AseguradoraLAYO.dto.HogarDTOs.CrearCotizacionHogarDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.HogarDTOs.ObtenerHogarDTO;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionHogar;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.HogarRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones.HogarServicioImpl;
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
public class HogarTest {

    @Autowired
    private HogarServicioImpl hogarServicio;

    @Autowired
    private HogarRepo hogarRepo;

    @BeforeEach
    public void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hogar.json");
        assertNotNull(inputStream, "Archivo hogar.json no encontrado");

        List<CotizacionHogar> cotizaciones = objectMapper.readValue(inputStream, new TypeReference<List<CotizacionHogar>>() {});

        // Limpiar el repositorio antes de cada prueba
        hogarRepo.deleteAll();
        hogarRepo.saveAll(cotizaciones);
    }

    @Test
    public void testListarHogares() throws Exception {
        List<ObtenerHogarDTO> cotizaciones = hogarServicio.listarHogares();
        assertFalse(cotizaciones.isEmpty(), "La lista de cotizaciones no debe estar vacía");
    }

    @Test
    public void testCrearCotizacionHogar() throws Exception {
        CrearCotizacionHogarDTO crearCotizacionHogarDTO = new CrearCotizacionHogarDTO(
                Aseguradora.BOLIVAR,
                "Carlos Torres",
                "987654321",
                "carlos@example.com",
                "3123456789",
                "Calle Principal 456",
                LocalDateTime.of(1990, 5, 20, 10, 30),
                5000000.0f,
                1000000.0f,
                200000.0f
        );

        // Ejecutar la creación
        hogarServicio.crearCotizacionHogar(crearCotizacionHogarDTO);

        // Verificar la cotización creada
        CotizacionHogar cotizacionGuardada = hogarRepo.buscarPorCedula("987654321")
                .orElseThrow(() -> new Exception("Cotización no encontrada"));
        assertEquals("Carlos Torres", cotizacionGuardada.getNommbre(), "El nombre debe coincidir");
    }

    @Test
    public void testEliminarCotizacionHogar() throws Exception {
        CotizacionHogar cotizacionGuardada = hogarRepo.buscarPorCedula("12345678")
                .orElseThrow(() -> new Exception("Cotización no encontrada"));

        // Ejecutar la eliminación
        hogarServicio.eliminarCotizacionHogar(cotizacionGuardada.getCedula());

        // Verificar que la cotización ha sido eliminada
        assertFalse(hogarRepo.buscarPorCedula("123456789").isPresent(), "La cotización debe haber sido eliminada");
    }

    @Test
    public void testEliminarCotizacionNoExistente() {
        String cedulaNoExistente = "999999999";
        Exception exception = assertThrows(Exception.class, () -> hogarServicio.eliminarCotizacionHogar(cedulaNoExistente));
        assertEquals("No se encontró una cotización con el id proporcionado.", exception.getMessage());
    }
}
