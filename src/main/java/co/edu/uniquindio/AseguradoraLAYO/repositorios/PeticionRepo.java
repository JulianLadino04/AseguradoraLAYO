package co.edu.uniquindio.AseguradoraLAYO.repositorios;

import co.edu.uniquindio.AseguradoraLAYO.modelo.documentos.Peticion;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeticionRepo extends  MongoRepository<Peticion, String> {

    @Query("{ 'cedula' : ?0 }")
    Optional<Peticion> buscarPorCedula(String cedula);

    @Query("{ 'email' : ?0 }")
    Optional<Peticion> buscarPorCorreo(String correo);

    @Query("{ '_id' : ?0 }")
    Optional<Peticion> findById(ObjectId id);
}
