package co.edu.uniquindio.AseguradoraLAYO;

import co.edu.uniquindio.AseguradoraLAYO.dto.PeticionDTOs.CrearPeticionDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.PeticionDTOs.ObtenerPeticionDTO;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.Peticion;
import co.edu.uniquindio.AseguradoraLAYO.repositorios.PeticionRepo;
import co.edu.uniquindio.AseguradoraLAYO.servicios.implementaciones.PeticionServicioImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PeticionTest {

    @Autowired
    private PeticionServicioImpl peticionServicio;

    @Autowired
    private PeticionRepo peticionRepo;

    @BeforeEach
    public void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Cargar el archivo JSON desde el directorio de recursos
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("peticion.json");
        assertNotNull(inputStream, "Archivo peticion.json no encontrado");

        List<Peticion> peticiones = objectMapper.readValue(inputStream, new TypeReference<List<Peticion>>() {});

        // Limpiar el repositorio antes de agregar nuevos registros
        peticionRepo.deleteAll();
        peticionRepo.saveAll(peticiones);
    }

    @Test
    public void testListarPeticiones() throws Exception {
        List<ObtenerPeticionDTO> peticiones = peticionServicio.listarPeticiones();
        assertFalse(peticiones.isEmpty(), "La lista de peticiones no debe estar vacía");
    }

    @Test
    public void testCrearPeticion() throws Exception {
        CrearPeticionDTO crearPeticionDTO = new CrearPeticionDTO(
                "Juan Perez",
                "987654351",
                "juan.perez@example.com",
                "3109876543",
                "Vehículo",
                "Quiero asegurar mi vehículo."
        );

        // Ejecutar el método de crear petición
        peticionServicio.crearPeticion(crearPeticionDTO);

        // Verificar que la petición fue guardada en el repositorio
        Peticion peticionGuardada = peticionRepo.buscarPorCedula("987654351")
                .orElseThrow(() -> new Exception("Petición no encontrada"));
        assertEquals("Juan Perez", peticionGuardada.getNombre(), "El nombre debe coincidir");
    }

    @Test
    public void testEliminarPeticion() throws Exception {
        // Buscar una petición existente
        Peticion peticionGuardada = peticionRepo.buscarPorCedula("123456789")
                .orElseThrow(() -> new Exception("Petición no encontrada"));

        // Ejecutar el método para eliminar la petición
        peticionServicio.eliminarPeticion(peticionGuardada.getCedula());

        // Verificar que la petición ha sido eliminada
        assertFalse(peticionRepo.buscarPorCedula("123456789").isPresent(), "La petición debe haber sido eliminada");
    }

    @Test
    public void testEliminarPeticionNoExistente() {
        // Intentar eliminar una petición con cédula inexistente
        String cedulaNoExistente = "000000000";
        Exception exception = assertThrows(Exception.class, () -> peticionServicio.eliminarPeticion(cedulaNoExistente));
        assertEquals("No se encontró una petición con la cédula proporcionada", exception.getMessage());
    }
}
