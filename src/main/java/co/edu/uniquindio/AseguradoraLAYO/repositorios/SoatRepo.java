package co.edu.uniquindio.AseguradoraLAYO.repositorios;

import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.SOAT;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SoatRepo extends MongoRepository<SOAT, String> {
    @Query("{ 'email' : ?0 }")
    Optional<SOAT> buscarSoatPorCorreo(String correo);

    @Query("{ 'numeroPlaca' : ?0 }")
    Optional<SOAT> buscarSoatPorPlaca(String placa);
}
