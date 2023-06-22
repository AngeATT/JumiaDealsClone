package ci.jumia.deals.services;

import ci.jumia.deals.entities.Categorie;
import ci.jumia.deals.repositories.CategorieRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategorieService {
  @Autowired
  CategorieRepository categorieRepository;
  public List<Categorie> getAllCategories(){
    return categorieRepository.findAll();
  }
}
