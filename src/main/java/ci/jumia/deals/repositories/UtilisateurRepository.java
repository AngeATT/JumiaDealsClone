package ci.jumia.deals.repositories;

import ci.jumia.deals.entities.UtilisateurEntity;
import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends CrudRepository<UtilisateurEntity,String> {

  public UtilisateurEntity findByEmail(String email);
}
