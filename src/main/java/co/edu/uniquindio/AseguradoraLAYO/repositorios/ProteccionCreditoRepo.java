package co.edu.uniquindio.AseguradoraLAYO.repositorios;

import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionProteccionCredito;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProteccionCreditoRepo extends  MongoRepository<CotizacionProteccionCredito, String> {

    @Query("{ 'cedula' : ?0 }")
    Optional<CotizacionProteccionCredito> buscarCuentaPorCedula(String cedula);

    @Query("{ 'email' : ?0 }")
    Optional<CotizacionProteccionCredito> buscarCuentaPorCorreo(String correo);

    @Query("{ '_id' : ?0 }")
    Optional<CotizacionProteccionCredito> findById(ObjectId id);
}
