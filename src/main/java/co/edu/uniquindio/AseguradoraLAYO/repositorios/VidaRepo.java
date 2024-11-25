package co.edu.uniquindio.AseguradoraLAYO.repositorios;

import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionVida;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VidaRepo extends  MongoRepository<CotizacionVida, String> {

    @Query("{ 'cedula' : ?0 }")
    Optional<CotizacionVida> buscarCuentaPorCedula(String cedula);

    @Query("{ 'email' : ?0 }")
    Optional<CotizacionVida> buscarCuentaPorCorreo(String correo);

    @Query("{ '_id' : ?0 }")
    Optional<CotizacionVida> findById(ObjectId id);
}
