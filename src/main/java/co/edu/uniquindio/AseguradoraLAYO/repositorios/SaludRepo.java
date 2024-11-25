package co.edu.uniquindio.AseguradoraLAYO.repositorios;

import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionSalud;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaludRepo extends  MongoRepository<CotizacionSalud, String> {

    @Query("{ 'cedula' : ?0 }")
    Optional<CotizacionSalud> buscarCuentaPorCedula(String cedula);

    @Query("{ 'email' : ?0 }")
    Optional<CotizacionSalud> buscarCuentaPorCorreo(String correo);

    @Query("{ '_id' : ?0 }")
    Optional<CotizacionSalud> findById(ObjectId id);
}
