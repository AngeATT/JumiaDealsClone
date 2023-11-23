package ci.jumia.deals.services;

import ci.jumia.deals.entities.user.TokenStatut;
import ci.jumia.deals.entities.user.UserUtilities;
import ci.jumia.deals.entities.user.UtilisateurEntity;
import ci.jumia.deals.exception.UserNotFoundException;
import ci.jumia.deals.repositories.UserUtilitiesRepository;
import ci.jumia.deals.repositories.UtilisateurRepository;
import ci.jumia.deals.security.FormulaireEnregistrement;
import ci.jumia.deals.services.interfaces.EmaillSender;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UtilisateurService implements UserDetailsService {
  @Autowired
  private final UtilisateurRepository utilisateurRepository;
  @Autowired
  private final UserUtilitiesRepository userUtilitiesRepository;
  private final PasswordEncoder passwordEncoder;
  private final EmaillSender emaillSender;

  @Transactional
  public UtilisateurEntity enregistrer(UtilisateurEntity utilisateur){
    return utilisateurRepository.save(utilisateur);
  }

  public UtilisateurEntity findByEmail(String email){
    Optional<UtilisateurEntity> userOptional = utilisateurRepository.findByEmail(email);
    if (userOptional.isPresent()){
      return userOptional.get();
    }else{
      throw new UserNotFoundException();
    }
  }
  @Transactional
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<UtilisateurEntity> userOptional = utilisateurRepository.findByEmail(email);
    if (userOptional.isPresent()){
      return userOptional.get();
    }else{
      throw new UserNotFoundException();
    }
  }

  public boolean userExistsByEmail(String email){
    return utilisateurRepository.existsByEmail(email);
  }

  public void saveUser(FormulaireEnregistrement formulaireEnregistrement){
    UtilisateurEntity utilisateurCree =  formulaireEnregistrement.toUtlisateur(passwordEncoder);
    utilisateurRepository.save(utilisateurCree);
    UserUtilities userUtilities = this.creerToken(utilisateurCree.getEmail());
    envoieMailInscription(utilisateurCree,userUtilities);
  }
  public UserUtilities creerToken(String email){
    UserUtilities userUtilities = new UserUtilities();
    userUtilities.setId(UUID.randomUUID());
    userUtilities.setToken(UUID.randomUUID().toString());
    userUtilities.setDateValidite(LocalDateTime.now().plusDays(1));
    userUtilities.setEmailUser(email);
    return userUtilitiesRepository.save(userUtilities);
  }

  private void envoieMailInscription(UtilisateurEntity utilisateur,UserUtilities userUtilities) {
    String lienApplication = "http://localhost:4200/";
    String email = utilisateur.getEmail();
    String template = "inscription-template";
    String lienInscription = String.format(lienApplication+"/activate/?token=%s",userUtilities.getToken());
    Context context = new Context();
    context.setVariable("lien",lienInscription);
    emaillSender.envoyerEmailAvecTemplate(email,"Confirmer inscription",template,context);
  }

  @Transactional
  public TokenStatut activerUtilisateur(String token){
    Optional<UserUtilities> byToken = this.userUtilitiesRepository.findByToken(token);
    if(byToken.isEmpty()){
      return TokenStatut.INVALID;
    }
    UserUtilities userUtilities = byToken.get();
    if(userUtilities.getDateValidite().isAfter(LocalDateTime.now())){
      UtilisateurEntity utilisateur = this.utilisateurRepository
              .findByEmail(userUtilities.getEmailUser())
              .orElseThrow(UserNotFoundException::new);
      utilisateur.setActive(true);
      utilisateur.setConfirme(true);
      this.enregistrer(utilisateur);
      userUtilitiesRepository.delete(userUtilities);
      return TokenStatut.VALID;
    }else{
      return TokenStatut.EXPIRED;
    }
  }

  public TokenStatut renvoyerToken(String token) {
    Optional<UserUtilities> userUtilitiesOptional = this.userUtilitiesRepository
            .findByToken(token);
    if(userUtilitiesOptional.isEmpty()){
      return TokenStatut.INVALID;
    }else {
      UserUtilities userUtilities = userUtilitiesOptional.get();
      this.userUtilitiesRepository.delete(userUtilities);
      UserUtilities nouveauToken = this.creerToken(userUtilities.getEmailUser());
      UtilisateurEntity utilisateur = this.utilisateurRepository
              .findByEmail(userUtilities.getEmailUser())
              .orElseThrow(UserNotFoundException::new);
      this.envoieMailInscription(utilisateur,nouveauToken);
      return TokenStatut.SENT;
    }

  }
}
