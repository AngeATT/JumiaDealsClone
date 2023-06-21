package ci.jumia.deals.repositories;

import ci.jumia.deals.entities.Annonce;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnnonceRepository extends MongoRepository<Annonce,String> {

}
