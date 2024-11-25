package co.edu.uniquindio.AseguradoraLAYO.repositorios;

import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionResponsabilidadCivil;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsabilidadCivilRepo extends  MongoRepository<CotizacionResponsabilidadCivil, String> {

    @Query("{ 'cedula' : ?0 }")
    Optional<CotizacionResponsabilidadCivil> buscarCuentaPorCedula(String cedula);

    @Query("{ 'email' : ?0 }")
    Optional<CotizacionResponsabilidadCivil> buscarCuentaPorCorreo(String correo);

    @Query("{ '_id' : ?0 }")
    Optional<CotizacionResponsabilidadCivil> findById(ObjectId id);
}
