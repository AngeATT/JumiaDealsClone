package ci.jumia.deals.repositories;

import ci.jumia.deals.entities.Categorie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends MongoRepository<Categorie,String> {

}
