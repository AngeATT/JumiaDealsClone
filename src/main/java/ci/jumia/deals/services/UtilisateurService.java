package ci.jumia.deals.services;

import ci.jumia.deals.entities.UtilisateurEntity;
import ci.jumia.deals.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
  @Autowired
  UtilisateurRepository utilisateurRepository;

  public UtilisateurEntity createAnnonceur(UtilisateurEntity utilisateur){
    return utilisateurRepository.save(utilisateur);
  }

  public UtilisateurEntity findByEmail(String email){
    return utilisateurRepository.findByEmail(email);
  }

}
