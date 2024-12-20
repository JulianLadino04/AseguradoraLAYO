package co.edu.uniquindio.AseguradoraLAYO.repositorios;

import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.CotizacionHogar;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HogarRepo extends  MongoRepository<CotizacionHogar, String> {

    @Query("{ 'cedula' : ?0 }")
    Optional<CotizacionHogar> buscarPorCedula(String cedula);

    @Query("{ 'email' : ?0 }")
    Optional<CotizacionHogar> buscarPorCorreo(String correo);

    @Query("{ '_id' : ?0 }")
    Optional<CotizacionHogar> findById(ObjectId id);
}
