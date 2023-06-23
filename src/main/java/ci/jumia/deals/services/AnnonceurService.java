package ci.jumia.deals.services;

import ci.jumia.deals.entities.Annonceur;
import ci.jumia.deals.repositories.AnnonceurRepository;
import ci.jumia.deals.rest.AnnonceurController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnonceurService {
  @Autowired
  AnnonceurRepository annonceurRepository;

  public Annonceur createAnnonceur(Annonceur annonceur){
    return annonceurRepository.save(annonceur);
  }
}
