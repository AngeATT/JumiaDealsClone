package ci.jumia.deals.rest;

import ci.jumia.deals.dto.UserDto;
import ci.jumia.deals.entities.UtilisateurEntity;
import ci.jumia.deals.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilisateurController {
  @Autowired
  UtilisateurService utilisateurService;
  @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(code= HttpStatus.CREATED)
  public UtilisateurEntity createAnnonceur(@RequestBody UtilisateurEntity utilisateur){
    return utilisateurService.createAnnonceur(utilisateur);
  }
  @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(code= HttpStatus.OK)
  public UtilisateurEntity loginUser(@RequestBody UserDto utilisateur){
    return new UtilisateurEntity();
    //TODO : changer cette fct
  }
}
