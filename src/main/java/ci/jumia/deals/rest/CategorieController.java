package ci.jumia.deals.rest;

import ci.jumia.deals.entities.Categorie;
import ci.jumia.deals.entities.SousCategorie;
import ci.jumia.deals.services.CategorieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategorieController {
  @Autowired
  CategorieService categorieService;
  @GetMapping(path ="/categories",produces = MediaType.APPLICATION_JSON_VALUE)
  List<Categorie> getAllCategories(){
    return categorieService.getAllCategories();
  }
}
