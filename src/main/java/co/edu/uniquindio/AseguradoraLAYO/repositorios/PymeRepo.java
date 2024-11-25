package co.edu.uniquindio.AseguradoraLAYO.repositorios;

import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionPyme;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PymeRepo extends  MongoRepository<CotizacionPyme, String> {

    @Query("{ 'cedula' : ?0 }")
    Optional<CotizacionPyme> buscarCuentaPorCedula(String cedula);

    @Query("{ 'email' : ?0 }")
    Optional<CotizacionPyme> buscarCuentaPorCorreo(String correo);

    @Query("{ '_id' : ?0 }")
    Optional<CotizacionPyme> findById(ObjectId id);
}
