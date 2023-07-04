package ci.jumia.deals.rest;

import ci.jumia.deals.entities.Annonce;
import ci.jumia.deals.services.AnnonceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnonceController {
  @Autowired
  AnnonceService annonceService;
  //TODO : retourner l'id de ce qu'on a crée au lieu de l'entité
  @PostMapping(path = "/addAnnonce", consumes = "application/json")
  @ResponseStatus(code= HttpStatus.CREATED)
  public Annonce addAnnonce(@RequestBody Annonce annonce) {
    return annonceService.createAnnonce(annonce);
  }

  @GetMapping(path = "/annonces",produces = "application/json")
  public List<Annonce> getAllAnnonces(){
    return annonceService.getAllAnnonce();
  }
}
