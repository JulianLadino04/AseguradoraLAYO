package co.edu.uniquindio.AseguradoraLAYO;

import co.edu.uniquindio.AseguradoraLAYO.dto.PymeDTOs.CrearCotizacionPymeDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.PymeDTOs.ObtenerPymeDTO;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionPyme;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.TipoInmueble;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.PymeRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones.PymeServicioImpl;
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
public class PymeTest {

    @Autowired
    private PymeServicioImpl pymeServicio;

    @Autowired
    private PymeRepo pymeRepo;

    @BeforeEach
    public void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Cargar los datos desde el archivo JSON
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("pyme.json");
        assertNotNull(inputStream, "Archivo pyme.json no encontrado");

        List<CotizacionPyme> cotizaciones = objectMapper.readValue(inputStream, new TypeReference<List<CotizacionPyme>>() {});

        // Limpiar el repositorio antes de cada prueba
        pymeRepo.deleteAll();
        pymeRepo.saveAll(cotizaciones);
    }

    @Test
    public void testCrearCotizacionPyme() throws Exception {
        CrearCotizacionPymeDTO nuevaCotizacionDTO = new CrearCotizacionPymeDTO(
                Aseguradora.BOLIVAR,
                "Comercial XYZ",
                "987654321",
                "comercial@example.com",
                "3123456789",
                "Calle Ficticia 123",
                LocalDateTime.of(1985, 8, 15, 9, 0),
                1000000.0f,  // Valor mercancia
                500000.0f,   // Valor maquinaria
                300000.0f,   // Valor comercial
                200000.0f,   // Valor electrico
                150000.0f,   // Valor muebles
                TipoInmueble.ARRENDADO
        );

        pymeServicio.crearCotizacionPyme(nuevaCotizacionDTO);

        Optional<CotizacionPyme> cotizacionOpt = pymeRepo.buscarPorCedula("987654321");
        assertTrue(cotizacionOpt.isPresent(), "La cotización debe existir en la base de datos");
        assertEquals("Comercial XYZ", cotizacionOpt.get().getNombre(), "El nombre debe coincidir");
    }

    @Test
    public void testEliminarCotizacionPyme() throws Exception {
        // Insertar una cotización antes de probar la eliminación
        CotizacionPyme cotizacion = CotizacionPyme.builder()
                .aseguradora(Aseguradora.SURA)
                .nombre("Empresa ABC")
                .cedula("123456789")
                .correo("empresa@example.com")
                .telefono("3145678901")
                .direccion("Carrera 25 #45-67")
                .fechaNacimiento(LocalDateTime.of(1992, 10, 10, 10, 0))
                .valorMercancia(1200000.0f)
                .valorMaquinaria(600000.0f)
                .valorComercial(350000.0f)
                .valorElectrico(250000.0f)
                .valorMuebles(180000.0f)
                .tipo(TipoInmueble.PROPIO)
                .build();
        pymeRepo.save(cotizacion);

        // Eliminar la cotización
        pymeServicio.eliminarCotizacionPyme("123456789");

        assertFalse(pymeRepo.buscarPorCedula("123456789").isPresent(), "La cotización debe haber sido eliminada");
    }

    @Test
    public void testListarPymes() throws Exception {
        // Listar cotizaciones
        List<ObtenerPymeDTO> lista = pymeServicio.listarPymes();
        assertFalse(lista.isEmpty(), "La lista no debe estar vacía");
    }
}
