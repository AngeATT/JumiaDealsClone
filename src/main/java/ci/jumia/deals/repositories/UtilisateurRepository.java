package ci.jumia.deals.repositories;

import ci.jumia.deals.entities.user.UtilisateurEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UtilisateurRepository extends MongoRepository<UtilisateurEntity,String> {

  Optional<UtilisateurEntity> findByEmail(String s);
  Boolean existsByEmail(String email);
}
