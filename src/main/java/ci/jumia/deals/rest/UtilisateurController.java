package ci.jumia.deals.rest;

import ci.jumia.deals.entities.user.UtilisateurEntity;
import ci.jumia.deals.services.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UtilisateurController {
  @Autowired
  UtilisateurService utilisateurService;
  @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(code= HttpStatus.CREATED)
  public UtilisateurEntity createAnnonceur(@RequestBody @Valid UtilisateurEntity utilisateur){
    return utilisateurService.createAnnonceur(utilisateur);
  }

  @PostMapping(path = "/activate/{token}")
  @ResponseStatus(code = HttpStatus.OK)
  public void activerAccount(@PathVariable @Valid String token){
    utilisateurService.activerUtilisateur(token);
  }
}
