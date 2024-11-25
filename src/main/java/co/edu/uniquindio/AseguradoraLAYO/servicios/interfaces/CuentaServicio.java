package co.edu.uniquindio.AseguradoraLAYO.servicios.interfaces;

import co.edu.uniquindio.AseguradoraLAYO.dto.CuentaDTOs.*;
import co.edu.uniquindio.AseguradoraLAYO.dto.TokenDTOs.TokenDTO;

import java.util.List;

public interface CuentaServicio {

    void crearCuenta(CrearCuentaDTO cuenta) throws Exception;

    String editarCuenta(EditarCuentaDTO cuenta) throws Exception;

    String eliminarCuenta(String id) throws Exception;

    InformacionCuentaDTO obtenerInformacionCuenta(String id) throws Exception;

    TokenDTO refreshToken(TokenDTO tokenDTO) throws Exception;

    String enviarCodigoRecuperacionPassword(EnviarCodigoDTO enviarCodigoDTO) throws Exception;

    String cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception;

    TokenDTO iniciarSesion(LoginDTO loginDTO) throws Exception;

    List<ItemCuentaDTO> listarCuentas() throws Exception;

    String activarCuenta(ValidarCuentaDTO validarCuentaDTO) throws Exception;
}
