package ci.jumia.deals.security;

import ci.jumia.deals.MessageResponse;
import ci.jumia.deals.entities.UtilisateurEntity;
import ci.jumia.deals.security.jwt.JwtUtils;
import ci.jumia.deals.services.UtilisateurService;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/auth")
@RestController
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  UtilisateurService utilisateurService;
  @Autowired
  JwtUtils jwtUtils;
  @PostMapping(path = "/login")
  @ResponseStatus(HttpStatus.OK)
  ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) throws UsernameNotFoundException {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken( loginRequest.email(),
                loginRequest.password() ));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    UtilisateurEntity utilisateur = (UtilisateurEntity) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(utilisateur);
    List<String> roles = utilisateur.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority).toList();

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(
        new UserInfoResponse(utilisateur.getAnnonceurId(),
            utilisateur.getEmail(),
            roles)
    );
  }

  @GetMapping(path = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  ResponseEntity<?> loginTest(){
    return ResponseEntity.ok().body(
        new UserInfoResponse(
            "ange",
            "angelo", Collections.EMPTY_LIST
        )
    );
  }

  @PostMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<?> enregistrement(@Valid @RequestBody FormulaireEnregistrement formulaireEnregistrement ){
     if (utilisateurService.userExistsByEmail(formulaireEnregistrement.email)){
      return ResponseEntity.badRequest().body(("Email déjà utilisée"));
    }else{
       utilisateurService.enregistrementUtilisateur(formulaireEnregistrement);

       return ResponseEntity.ok().body("Utilisateur enregistré avec succès");
     }
  }

  @PostMapping("/signout")
  public ResponseEntity<?> deconnexion(){
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(new MessageResponse("Deconnexion réussie"));
  }


}
