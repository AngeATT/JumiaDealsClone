package ci.jumia.deals.rest;

import ci.jumia.deals.entities.Annonceur;
import ci.jumia.deals.services.AnnonceurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnonceurController {
  @Autowired
  AnnonceurService annonceurService;
  @PostMapping(path = "/annonceur", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(code= HttpStatus.CREATED)
  public Annonceur createAnnonceur(@RequestBody Annonceur annonceur){
    return annonceurService.createAnnonceur(annonceur);
  }
}
