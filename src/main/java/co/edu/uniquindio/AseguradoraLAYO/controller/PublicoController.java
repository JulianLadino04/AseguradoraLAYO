package co.edu.uniquindio.AseguradoraLAYO.controller;

import co.edu.uniquindio.AseguradoraLAYO.dto.CuentaDTOs.*;
import co.edu.uniquindio.AseguradoraLAYO.dto.TokenDTOs.MensajeDTO;
import co.edu.uniquindio.AseguradoraLAYO.dto.TokenDTOs.TokenDTO;
import co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces.CuentaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/publico")
public class PublicoController {

    private final CuentaServicio cuentaServicio;

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesion(@Valid @RequestBody LoginDTO loginDTO) throws Exception{
        TokenDTO token = cuentaServicio.iniciarSesion(loginDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, token));
    }

    @PostMapping("/refresh")
    public ResponseEntity<MensajeDTO<TokenDTO>> refresh(@RequestBody TokenDTO tokenDTO) throws Exception {
        TokenDTO nuevoToken = cuentaServicio.refreshToken(tokenDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, nuevoToken));
    }

    @PostMapping("/enviar-codigo-recuperacion")
    public ResponseEntity<MensajeDTO<String>> enviarCodigoRecuperacionPassword(@Valid @RequestBody EnviarCodigoDTO enviarCodigoDTO) throws Exception {
        cuentaServicio.enviarCodigoRecuperacionPassword(enviarCodigoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Se ha enviado un código de Verificación a su correo, con una duración de 15 minutos"));
    }


    @PostMapping("/cambiar-password")
    public ResponseEntity<MensajeDTO<String>> cambiarPassword(@Valid @RequestBody CambiarPasswordDTO cambiarPasswordDTO) throws Exception {
        cuentaServicio.cambiarPassword(cambiarPasswordDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "La contraseña se ha cambiado correctamente"));
    }

    @PostMapping("/crear-cuenta")
    public ResponseEntity<MensajeDTO<String>> crearCuenta(@Valid @RequestBody CrearCuentaDTO cuenta) throws Exception{
        cuentaServicio.crearCuenta(cuenta);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta creada exitosamente, se envio un codigo de activación de la cuenta a su correo registrado"));
    }

    @PostMapping("/activar-cuenta")
    public ResponseEntity<MensajeDTO<String>> activarCuenta(@Valid @RequestBody ValidarCuentaDTO cuenta) throws Exception{
        cuentaServicio.activarCuenta(cuenta);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta activada exitosamente"));
    }

}
