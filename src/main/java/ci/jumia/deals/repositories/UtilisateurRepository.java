package ci.jumia.deals.repositories;

import ci.jumia.deals.entities.user.UtilisateurEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends CrudRepository<UtilisateurEntity,String> {

  Optional<UtilisateurEntity> findByEmail(String s);
  Boolean existsByEmail(String email);
}
