package co.edu.uniquindio.AseguradoraLAYO.controller;

import co.edu.uniquindio.AseguradoraLAYO.dto.AutosDTOs.ObtenerAutosDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.HogarDTOs.ObtenerHogarDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.PeticionDTOs.ObtenerPeticionDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.ProteccionCreditoDTOs.ObtenerProteccionCreditoDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.PymeDTOs.ObtenerPymeDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.ResponsabilidadCivilDTOs.ObtenerResponsabilidadCivilDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.SaludDTOs.ObtenerSaludDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.SoatDTOs.ObtenerSoatDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.VidaDTOs.ObtenerVidaDTO;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.*;
import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.*;
import co.edu.uniquindio.AseguradoraLAYO.dto.TokenDTOs.MensajeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdministradorController {

    private final AutosServicio autosServicio;
    private final HogarServicio hogarServicio;
    private final ProteccionCreditoServicio proteccionCreditoServicio;
    private final PymeServicio pymeServicio;
    private final ResponsabilidadCivilServicio responsabilidadCivilServicio;
    private final SaludServicio saludServicio;
    private final VidaServicio vidaServicio;
    private final SoatServicio soatServicio;
    private final PeticionServicio peticionServicio;

    // Métodos para listar

    @GetMapping("/autos/listar")
    public ResponseEntity<MensajeDTO<List<ObtenerAutosDTO>>> listarAutos() throws Exception {
        List<ObtenerAutosDTO> autos = autosServicio.listarAutos();
        return ResponseEntity.ok(new MensajeDTO<>(false, autos));
    }

    @GetMapping("/hogar/listar")
    public ResponseEntity<MensajeDTO<List<ObtenerHogarDTO>>> listarHogar() throws Exception {
        List<ObtenerHogarDTO> hogares = hogarServicio.listarHogares();
        return ResponseEntity.ok(new MensajeDTO<>(false, hogares));
    }

    @GetMapping("/proteccion-credito/listar")
    public ResponseEntity<MensajeDTO<List<ObtenerProteccionCreditoDTO>>> listarProteccionCredito() throws Exception {
        List<ObtenerProteccionCreditoDTO> protecciones = proteccionCreditoServicio.listarProteccionCreditos();
        return ResponseEntity.ok(new MensajeDTO<>(false, protecciones));
    }

    @GetMapping("/pyme/listar")
    public ResponseEntity<MensajeDTO<List<ObtenerPymeDTO>>> listarPyme() throws Exception {
        List<ObtenerPymeDTO> pymes = pymeServicio.listarPymes();
        return ResponseEntity.ok(new MensajeDTO<>(false, pymes));
    }

    @GetMapping("/responsabilidad-civil/listar")
    public ResponseEntity<MensajeDTO<List<ObtenerResponsabilidadCivilDTO>>> listarResponsabilidadCivil() throws Exception {
        List<ObtenerResponsabilidadCivilDTO> responsabilidades = responsabilidadCivilServicio.listarResponsabilidadCivil();
        return ResponseEntity.ok(new MensajeDTO<>(false, responsabilidades));
    }

    @GetMapping("/salud/listar")
    public ResponseEntity<MensajeDTO<List<ObtenerSaludDTO>>> listarSalud() throws Exception {
        List<ObtenerSaludDTO> salud = saludServicio.listarSalud();
        return ResponseEntity.ok(new MensajeDTO<>(false, salud));
    }

    @GetMapping("/soat/listar")
    public ResponseEntity<MensajeDTO<List<ObtenerSoatDTO>>> listarSoat() throws Exception {
        List<ObtenerSoatDTO> soats = soatServicio.listarSoat();
        return ResponseEntity.ok(new MensajeDTO<>(false, soats));
    }

    @GetMapping("/vida/listar")
    public ResponseEntity<MensajeDTO<List<ObtenerVidaDTO>>> listarVida() throws Exception {
        List<ObtenerVidaDTO> vidas = vidaServicio.listarVida();
        return ResponseEntity.ok(new MensajeDTO<>(false, vidas));
    }

    @GetMapping("/peticion/listar")
    public ResponseEntity<MensajeDTO<List<ObtenerPeticionDTO>>> listarPeticion() throws Exception {
        List<ObtenerPeticionDTO> peticiones = peticionServicio.listarPeticiones();
        return ResponseEntity.ok(new MensajeDTO<>(false, peticiones));
    }

    // Métodos para eliminar

    @DeleteMapping("/autos/eliminar/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarAuto(@PathVariable String id) {
        try {
            autosServicio.eliminarCotizacionAutos(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cotizacion Auto eliminada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeDTO<>(true, "Error al eliminar auto: " + e.getMessage()));
        }
    }

    @DeleteMapping("/hogar/eliminar/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarHogar(@PathVariable String id) {
        try {
            hogarServicio.eliminarCotizacionHogar(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cotizacion Hogar eliminada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeDTO<>(true, "Error al eliminar hogar: " + e.getMessage()));
        }
    }

    @DeleteMapping("/proteccion-credito/eliminar/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarProteccionCredito(@PathVariable String id) {
        try {
            proteccionCreditoServicio.eliminarCotizacionProteccionCredito(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cotizacion Proteccion Credito eliminada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeDTO<>(true, "Error al eliminar protección de crédito: " + e.getMessage()));
        }
    }

    @DeleteMapping("/pyme/eliminar/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarPyme(@PathVariable String id) {
        try {
            pymeServicio.eliminarCotizacionPyme(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cotizacion Pyme eliminada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeDTO<>(true, "Error al eliminar pyme: " + e.getMessage()));
        }
    }

    @DeleteMapping("/responsabilidad-civil/eliminar/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarResponsabilidadCivil(@PathVariable String id) {
        try {
            responsabilidadCivilServicio.eliminarCotizacionResponsabilidadCivil(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cotizacion Responsabilidad Civil eliminada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeDTO<>(true, "Error al eliminar responsabilidad civil: " + e.getMessage()));
        }
    }

    @DeleteMapping("/salud/eliminar/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarSalud(@PathVariable String id) {
        try {
            saludServicio.eliminarCotizacionSalud(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cotizacion Salud eliminada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeDTO<>(true, "Error al eliminar salud: " + e.getMessage()));
        }
    }

    @DeleteMapping("/vida/eliminar/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarVida(@PathVariable String id) {
        try {
            vidaServicio.eliminarCotizacionVida(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cotizacion Vida eliminada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeDTO<>(true, "Error al eliminar vida: " + e.getMessage()));
        }
    }

    @DeleteMapping("/soat/eliminar/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarSoat(@PathVariable String id) {
        try {
            soatServicio.eliminarSoat(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Soat eliminado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeDTO<>(true, "Error al eliminar Soat: " + e.getMessage()));
        }
    }

    @DeleteMapping("/peticion/eliminar/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarPeticion(@PathVariable String id) {
        try {
            peticionServicio.eliminarPeticion(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Peticion eliminada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeDTO<>(true, "Error al eliminar Peticion: " + e.getMessage()));
        }
    }
}
