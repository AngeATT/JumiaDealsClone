package ci.jumia.deals.repositories;

import ci.jumia.deals.entities.Ville;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VilleRepository extends MongoRepository<Ville,String> {

}
