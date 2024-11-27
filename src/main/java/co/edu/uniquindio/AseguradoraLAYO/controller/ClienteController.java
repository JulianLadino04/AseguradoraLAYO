package co.edu.uniquindio.AseguradoraLAYO.controller;

import co.edu.uniquindio.AseguradoraLAYO.dto.AutosDTOs.CrearCotizacionAutoDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.HogarDTOs.CrearCotizacionHogarDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.ProteccionCreditoDTOs.CrearCotizacionProteccionCreditoDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.PymeDTOs.CrearCotizacionPymeDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.ResponsabilidadCivilDTOs.CrearCotizacionResponsabilidadCivilDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.SaludDTOs.CrearCotizacionSaludDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.SoatDTOs.CrearSoatDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.VidaDTOs.CrearCotizacionVidaDTO;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.*;
import co.edu.uniquindio.AseguradoraLAYO.dto.TokenDTOs.MensajeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cliente")
public class ClienteController {

    private final AutosServicio autosServicio;
    private final HogarServicio hogarServicio;
    private final ProteccionCreditoServicio proteccionCreditoServicio;
    private final PymeServicio pymeServicio;
    private final ResponsabilidadCivilServicio responsabilidadCivilServicio;
    private final SaludServicio saludServicio;
    private final VidaServicio vidaServicio;
    private final SoatServicio soatServicio;

    // Crear Cotización para Autos
    @PostMapping("/autos/cotizar")
    public ResponseEntity<MensajeDTO<String>> crearCotizacionAuto(@RequestBody CrearCotizacionAutoDTO cotizacionAutoDTO) {
        try {
            autosServicio.crearCotizacionAutos(cotizacionAutoDTO);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cotización para seguro de Autos creada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeDTO<>(true, "Error al crear cotización de auto: " + e.getMessage()));
        }
    }

    // Crear Cotización para Hogar
    @PostMapping("/hogar/cotizar")
    public ResponseEntity<MensajeDTO<String>> crearCotizacionHogar(@RequestBody CrearCotizacionHogarDTO cotizacionHogarDTO) {
        try {
            hogarServicio.crearCotizacionHogar(cotizacionHogarDTO);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cotización para seguro de Hogar creada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeDTO<>(true, "Error al crear cotización de hogar: " + e.getMessage()));
        }
    }

    // Crear Cotización para Protección de Crédito
    @PostMapping("/proteccion-credito/cotizar")
    public ResponseEntity<MensajeDTO<String>> crearCotizacionProteccionCredito(@RequestBody CrearCotizacionProteccionCreditoDTO cotizacionProteccionDTO) {
        try {
            proteccionCreditoServicio.crearCotizacionProteccionCredito(cotizacionProteccionDTO);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cotización para seguro de Protección de Crédito creada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeDTO<>(true, "Error al crear cotización de protección de crédito: " + e.getMessage()));
        }
    }

    // Crear Cotización para Pyme
    @PostMapping("/pyme/cotizar")
    public ResponseEntity<MensajeDTO<String>> crearCotizacionPyme(@RequestBody CrearCotizacionPymeDTO cotizacionPymeDTO) {
        try {
            pymeServicio.crearCotizacionPyme(cotizacionPymeDTO);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cotización para seguro de Pyme creada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeDTO<>(true, "Error al crear cotización de pyme: " + e.getMessage()));
        }
    }

    // Crear Cotización para Responsabilidad Civil
    @PostMapping("/responsabilidad-civil/cotizar")
    public ResponseEntity<MensajeDTO<String>> crearCotizacionResponsabilidadCivil(@RequestBody CrearCotizacionResponsabilidadCivilDTO cotizacionResponsabilidadCivilDTO) {
        try {
            responsabilidadCivilServicio.crearCotizacionResponsabilidadCivil(cotizacionResponsabilidadCivilDTO);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cotización para seguro de Responsabilidad Civil creada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeDTO<>(true, "Error al crear cotización de responsabilidad civil: " + e.getMessage()));
        }
    }

    // Crear Cotización para Salud
    @PostMapping("/salud/cotizar")
    public ResponseEntity<MensajeDTO<String>> crearCotizacionSalud(@RequestBody CrearCotizacionSaludDTO cotizacionSaludDTO) {
        try {
            saludServicio.crearCotizacionSalud(cotizacionSaludDTO);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cotización para seguro de Salud creada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeDTO<>(true, "Error al crear cotización de salud: " + e.getMessage()));
        }
    }

    // Crear Cotización para Vida
    @PostMapping("/vida/cotizar")
    public ResponseEntity<MensajeDTO<String>> crearCotizacionVida(@RequestBody CrearCotizacionVidaDTO cotizacionVidaDTO) {
        try {
            vidaServicio.crearCotizacionVida(cotizacionVidaDTO);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cotización para seguro de Vida creada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeDTO<>(true, "Error al crear cotización de vida: " + e.getMessage()));
        }
    }

    // Crear Cotización para Soat
    @PostMapping("/soat/cotizar")
    public ResponseEntity<MensajeDTO<String>> crearCotizacionSoat(@RequestBody CrearSoatDTO soatDTO) {
        try {
            soatServicio.crearSoat(soatDTO);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cotización para Soat creada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeDTO<>(true, "Error al crear Soat: " + e.getMessage()));
        }
    }
}
