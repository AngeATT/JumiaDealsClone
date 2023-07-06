package ci.jumia.deals.security;

import ci.jumia.deals.entities.UtilisateurEntity;
import ci.jumia.deals.services.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
  @Autowired
  UtilisateurService utilisateurService;
  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) throws UsernameNotFoundException {
    UtilisateurEntity entity = utilisateurService.findByEmail(loginRequest.userame());
    if (entity.getPassword() == loginRequest.password()){
      return ResponseEntity.ok().build();
    }else {
      return ResponseEntity.badRequest().build();
    }
  }
}
