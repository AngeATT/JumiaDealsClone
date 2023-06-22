package ci.jumia.deals.repositories;

import ci.jumia.deals.entities.Quartier;
import ci.jumia.deals.entities.Ville;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuartierRepository extends MongoRepository<Quartier,String> {

  List<Quartier> findAllByVille(String ville);
}
