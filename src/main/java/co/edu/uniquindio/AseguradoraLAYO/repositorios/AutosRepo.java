package co.edu.uniquindio.AseguradoraLAYO.repositorios;

import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionAutos;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutosRepo extends  MongoRepository<CotizacionAutos, String> {
    @Query("{ 'numeroPlaca' : ?0 }")
    Optional<CotizacionAutos> buscarPorPlaca(String placa);

    @Query("{ 'email' : ?0 }")
    Optional<CotizacionAutos> buscarCuentaPorCorreo(String correo);

    @Query("{ '_id' : ?0 }")
    Optional<CotizacionAutos> findById(ObjectId id);
}
