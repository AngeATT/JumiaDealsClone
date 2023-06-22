package ci.jumia.deals.repositories;

import ci.jumia.deals.entities.SousCategorie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface SousCategorieRepository extends MongoRepository<SousCategorie,String> {

}
