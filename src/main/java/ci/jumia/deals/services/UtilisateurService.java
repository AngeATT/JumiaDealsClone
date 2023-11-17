package ci.jumia.deals.services;

import ci.jumia.deals.ErrorMessageException;
import ci.jumia.deals.UserNotFoundException;
import ci.jumia.deals.entities.user.UserUtilities;
import ci.jumia.deals.entities.user.UtilisateurEntity;
import ci.jumia.deals.exception.ObjetNonTrouverException;
import ci.jumia.deals.repositories.UserUtilitiesRepository;
import ci.jumia.deals.repositories.UtilisateurRepository;
import ci.jumia.deals.security.FormulaireEnregistrement;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService implements UserDetailsService {
  @Autowired
  UtilisateurRepository utilisateurRepository;
  @Autowired
  UserUtilitiesRepository userUtilitiesRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  public UtilisateurEntity createAnnonceur(UtilisateurEntity utilisateur){
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

  public UtilisateurEntity enregistrementUtilisateur(FormulaireEnregistrement formulaireEnregistrement){
    UtilisateurEntity utilisateurCree =  formulaireEnregistrement.toUtlisateur(passwordEncoder);
    utilisateurRepository.save(utilisateurCree);
    UserUtilities userUtilities = new UserUtilities();
    userUtilities.setId(UUID.randomUUID());
    userUtilities.setToken(UUID.randomUUID().toString());
    userUtilities.setDateValidite(LocalDate.now().plusDays(1));
    userUtilitiesRepository.save(userUtilities);
    return utilisateurCree;
  }

  public void activerUtilisateur(String token){
    UserUtilities userUtilities = this.userUtilitiesRepository
            .findByToken(token)
            .orElseThrow(()->new ObjetNonTrouverException("Token invalide"));
    if(userUtilities.getDateValidite().isBefore(LocalDate.now())){
      UtilisateurEntity utilisateur = userUtilities.getUtilisateur();
      utilisateur.setActive(true);
      utilisateur.setConfirme(true);
      utilisateurRepository.save(utilisateur);
    }else{
      throw new ErrorMessageException("Token invalide");
    }
  }
}
