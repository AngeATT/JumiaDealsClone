package ci.jumia.deals.rest;

import ci.jumia.deals.entities.user.UtilisateurEntity;
import ci.jumia.deals.services.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UtilisateurController {
  @Autowired
  UtilisateurService utilisateurService;
  @PostMapping
  @ResponseStatus(code= HttpStatus.CREATED)
  public UtilisateurEntity createAnnonceur(@RequestBody @Valid UtilisateurEntity utilisateur){
    return utilisateurService.enregistrer(utilisateur);
  }
}
