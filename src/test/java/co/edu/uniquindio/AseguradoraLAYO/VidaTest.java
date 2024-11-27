package co.edu.uniquindio.AseguradoraLAYO;

import co.edu.uniquindio.AseguradoraLAYO.dto.VidaDTOs.CrearCotizacionVidaDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.VidaDTOs.ObtenerVidaDTO;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionVida;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.VidaRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones.VidaServicioImpl;
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
public class VidaTest {

    @Autowired
    private VidaServicioImpl vidaServicio;

    @Autowired
    private VidaRepo vidaRepo;

    @BeforeEach
    public void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Cargar los datos desde el archivo JSON
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("vida.json");
        assertNotNull(inputStream, "Archivo vida.json no encontrado");

        List<CotizacionVida> cotizaciones = objectMapper.readValue(inputStream, new TypeReference<List<CotizacionVida>>() {});

        // Limpiar el repositorio antes de cada prueba
        vidaRepo.deleteAll();
        vidaRepo.saveAll(cotizaciones);
    }

    @Test
    public void testCrearCotizacionVida() throws Exception {
        CrearCotizacionVidaDTO nuevaCotizacionDTO = new CrearCotizacionVidaDTO(
                Aseguradora.BOLIVAR,
                "Ana Gómez",
                "123456789",
                "ana.gomez@example.com",
                "3123456789",
                "Calle Ficticia 123",
                "Ingeniera",
                LocalDateTime.of(1985, 8, 15, 9, 0)
        );

        vidaServicio.crearCotizacionVida(nuevaCotizacionDTO);

        Optional<CotizacionVida> cotizacionOpt = vidaRepo.buscarPorCedula("123456789");
        assertTrue(cotizacionOpt.isPresent(), "La cotización debe existir en la base de datos");
        assertEquals("Ana Gómez", cotizacionOpt.get().getNommbre(), "El nombre debe coincidir");
    }

    @Test
    public void testEliminarCotizacionVida() throws Exception {
        // Insertar una cotización antes de probar la eliminación
        CotizacionVida cotizacion = CotizacionVida.builder()
                .aseguradora(Aseguradora.SURA)
                .nommbre("Pedro Rodríguez")
                .cedula("987654321")
                .correo("pedro@example.com")
                .telefono("3145678901")
                .direccion("Carrera 25 #45-67")
                .ocupacion("Arquitecto")
                .fechaNacimiento(LocalDateTime.of(1992, 10, 10, 10, 0))
                .build();
        vidaRepo.save(cotizacion);

        // Eliminar la cotización
        vidaServicio.eliminarCotizacionVida("987654321");

        assertFalse(vidaRepo.buscarPorCedula("987654321").isPresent(), "La cotización debe haber sido eliminada");
    }

    @Test
    public void testListarVida() throws Exception {
        // Listar cotizaciones
        List<ObtenerVidaDTO> lista = vidaServicio.listarVida();
        assertFalse(lista.isEmpty(), "La lista no debe estar vacía");
    }
}
