package ci.jumia.deals.security.controller;

import ci.jumia.deals.MessageResponse;
import ci.jumia.deals.entities.user.TokenStatut;
import ci.jumia.deals.entities.user.UtilisateurEntity;
import ci.jumia.deals.security.FormulaireEnregistrement;
import ci.jumia.deals.security.LoginRequest;
import ci.jumia.deals.security.UserInfoResponse;
import ci.jumia.deals.security.jwt.JwtUtils;
import ci.jumia.deals.services.UtilisateurService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(allowCredentials = "true",origins = "http://localhost:4200")
@RequestMapping(path = "/api/auth")
@RestController
public class AuthController {
  private final Logger logger = LoggerFactory.getLogger(AuthController.class);
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  UtilisateurService utilisateurService;
  @Autowired
  JwtUtils jwtUtils;

  @PostMapping(path = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) throws UsernameNotFoundException {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.email(),
                loginRequest.password() ));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    UtilisateurEntity utilisateur = (UtilisateurEntity) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(utilisateur);
    List<String> roles = utilisateur.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority).toList();

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(
        new UserInfoResponse(
            utilisateur.getEmail(),
            roles)
    );
  }

  @PostMapping(path = "/register")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<?> enregistrement(@Valid @RequestBody FormulaireEnregistrement formulaireEnregistrement ){
     if (utilisateurService.userExistsByEmail(formulaireEnregistrement.getEmail())){
      return ResponseEntity.badRequest().body(("Email déjà utilisée"));
    }else{
       utilisateurService.saveUser(formulaireEnregistrement);

       return ResponseEntity.ok().body("{"+  " \"message\" : \"enregistrement ok\"  " +"}");
     }
  }

  @PostMapping("/signout")
  public ResponseEntity<?> deconnexion(){
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    logger.info("utilisateur deconnecté");
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(new MessageResponse("Deconnexion réussie"));
  }

  @PostMapping(path = "/activate")
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<TokenStatut> activerAccount(@RequestBody @Valid String token){
    return ResponseEntity.ok(utilisateurService.activerUtilisateur(token));
  }

  @PostMapping(path = "/resend")
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<TokenStatut> resendToken(@RequestBody @Valid String token){
    return ResponseEntity.ok(utilisateurService.renvoyerToken(token));
  }

}
