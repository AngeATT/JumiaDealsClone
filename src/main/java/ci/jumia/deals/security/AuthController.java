package ci.jumia.deals.security;

import ci.jumia.deals.entities.UtilisateurEntity;
import ci.jumia.deals.services.UtilisateurService;
import jakarta.validation.Valid;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
  @Lazy
  @Autowired
  AuthenticationManager authenticationManager;
  @Lazy
  @Autowired
  UtilisateurService utilisateurService;
  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) throws UsernameNotFoundException {

    Authentication authentication =
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(),loginRequest.password()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    UtilisateurEntity utilisateur = (UtilisateurEntity) authentication.getPrincipal();

    return ResponseEntity.ok().body(
        new UserInfoResponse(utilisateur.getAnnonceurId(),
            utilisateur.getEmail(),
            utilisateur.getAuthorities())
    );
  }

  @GetMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  ResponseEntity<?> loginTest(){
    return ResponseEntity.ok().body(
        new UserInfoResponse(
            "ange",
            "angelo", Collections.EMPTY_LIST
        )
    );
  }

}
