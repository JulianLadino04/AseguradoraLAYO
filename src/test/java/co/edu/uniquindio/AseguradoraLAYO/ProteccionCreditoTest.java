package co.edu.uniquindio.AseguradoraLAYO;

import co.edu.uniquindio.AseguradoraLAYO.dto.ProteccionCreditoDTOs.CrearCotizacionProteccionCreditoDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.ProteccionCreditoDTOs.ObtenerProteccionCreditoDTO;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionProteccionCredito;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.ProteccionCreditoRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones.ProteccionCreditoServicioImpl;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.EmailServicio;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProteccionCreditoTest {

    @Autowired
    private ProteccionCreditoServicioImpl proteccionCreditoServicio;

    @Autowired
    private ProteccionCreditoRepo proteccionCreditoRepo;

    @Autowired
    private EmailServicio emailServicio;

    @BeforeEach
    public void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Cargar los datos desde el archivo JSON
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("proteccionCredito.json");
        assertNotNull(inputStream, "Archivo proteccionCredito.json no encontrado");

        List<CotizacionProteccionCredito> cotizaciones = objectMapper.readValue(inputStream, new TypeReference<List<CotizacionProteccionCredito>>() {});

        // Limpiar el repositorio antes de cada prueba
        proteccionCreditoRepo.deleteAll();
        proteccionCreditoRepo.saveAll(cotizaciones);
    }

    @Test
    public void testCrearCotizacionProteccionCredito() throws Exception {
        CrearCotizacionProteccionCreditoDTO nuevaCotizacionDTO = new CrearCotizacionProteccionCreditoDTO(
                Aseguradora.BOLIVAR,
                "Juan Perez",
                "123456789",
                "juan@example.com",
                "1234567890",
                "Calle 123",
                LocalDateTime.of(1990, 5, 20, 10, 30),
                50000.0f  // Valor deuda
        );

        proteccionCreditoServicio.crearCotizacionProteccionCredito(nuevaCotizacionDTO);

        Optional<CotizacionProteccionCredito> cotizacionOpt = proteccionCreditoRepo.buscarPorCedula("123456789");
        assertTrue(cotizacionOpt.isPresent(), "La cotización debe existir en la base de datos");
        assertEquals("Juan Perez", cotizacionOpt.get().getNommbre(), "El nombre debe coincidir");
    }

    @Test
    public void testEliminarCotizacionProteccionCredito() throws Exception {
        // Insertar una cotización antes de probar la eliminación
        CotizacionProteccionCredito cotizacion = CotizacionProteccionCredito.builder()
                .aseguradora(Aseguradora.SURA)
                .nommbre("Maria Lopez")
                .cedula("987654321")
                .correo("maria@example.com")
                .telefono("9876543210")
                .direccion("Carrera 45 #10-20")
                .fechaNacimiento(LocalDateTime.of(1990, 5, 20, 10, 30))
                .valorDeuda(100000.0f)
                .build();
        proteccionCreditoRepo.save(cotizacion);

        // Eliminar la cotización
        proteccionCreditoServicio.eliminarCotizacionProteccionCredito("987654321");

        assertFalse(proteccionCreditoRepo.buscarPorCedula("987654321").isPresent(), "La cotización debe haber sido eliminada");
    }

    @Test
    public void testListarProteccionCreditos() throws Exception {
        // Listar cotizaciones
        List<ObtenerProteccionCreditoDTO> lista = proteccionCreditoServicio.listarProteccionCreditos();
        assertFalse(lista.isEmpty(), "La lista no debe estar vacía");
    }
}
