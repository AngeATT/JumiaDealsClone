package ci.jumia.deals.services;

import ci.jumia.deals.UserNotFoundException;
import ci.jumia.deals.entities.UtilisateurEntity;
import ci.jumia.deals.repositories.UtilisateurRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService implements UserDetailsService {
  @Autowired
  UtilisateurRepository utilisateurRepository;

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
}
