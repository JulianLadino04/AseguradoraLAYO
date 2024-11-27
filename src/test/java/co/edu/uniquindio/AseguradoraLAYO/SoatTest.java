package co.edu.uniquindio.AseguradoraLAYO;

import co.edu.uniquindio.AseguradoraLAYO.dto.SoatDTOs.*;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.SOAT;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.TipoVehiculo;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.SoatRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones.SoatServicioImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SoatTest {

    @Autowired
    private SoatServicioImpl soatServicio;

    @Autowired
    private SoatRepo soatRepo;

    @BeforeEach
    public void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Cargar el archivo JSON desde el directorio de recursos
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("soat.json");
        assertNotNull(inputStream, "Archivo soat.json no encontrado");

        List<SOAT> soats = objectMapper.readValue(inputStream, new TypeReference<List<SOAT>>() {});

        // Limpiar el repositorio antes de agregar nuevos registros
        soatRepo.deleteAll();
        soatRepo.saveAll(soats);
    }

    @Test
    public void testListarSoat() throws Exception {
        List<ObtenerSoatDTO> soats = soatServicio.listarSoat();
        assertFalse(soats.isEmpty(), "La lista de SOAT no debe estar vacía");
    }

    @Test
    public void testCrearSoat() throws Exception {
        CrearSoatDTO crearSoatDTO = new CrearSoatDTO(
                "ABC143", "Julian Ladino", "1091884402", "3114420307", "Calle Ficticia 123", "julian@gmail.com", Aseguradora.BOLIVAR, TipoVehiculo.PARTICULAR
        );

        // Ejecutar el método de crear SOAT
        soatServicio.crearSoat(crearSoatDTO);

        // Verificar que el SOAT fue guardado en el repositorio
        SOAT soatGuardado = soatRepo.buscarSoatPorPlaca("ABC143").orElseThrow(() -> new Exception("SOAT no encontrado"));
        assertEquals("Julian Ladino", soatGuardado.getNombre(), "El nombre del titular debe coincidir");

    }

    @Test
    public void testEliminarSoat() throws Exception {
        // Buscar un SOAT ya guardado
        SOAT soatGuardado = soatRepo.buscarSoatPorPlaca("ABC123").orElseThrow(() -> new Exception("SOAT no encontrado"));

        // Ejecutar el método para eliminar SOAT
        soatServicio.eliminarSoat(soatGuardado.getNumeroPlaca());

        // Verificar que el SOAT ha sido eliminado
        assertFalse(soatRepo.buscarSoatPorPlaca("ABC123").isPresent(), "El SOAT debe haber sido eliminado");
    }

    @Test
    public void testEliminarSoatNoExistente() throws Exception {
        // Intentar eliminar un SOAT con placa no existente
        String placaNoExistente = "XYZ999";
        Exception exception = assertThrows(Exception.class, () -> soatServicio.eliminarSoat(placaNoExistente));
        assertEquals("No se encontró un SOAT con la placa: XYZ999", exception.getMessage());
    }
}
