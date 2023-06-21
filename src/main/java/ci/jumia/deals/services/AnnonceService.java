package ci.jumia.deals.services;

import ci.jumia.deals.entities.Annonce;
import ci.jumia.deals.repositories.AnnonceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnonceService {
  @Autowired
  AnnonceRepository annonceRepository;
  public Annonce createAnnonce(Annonce annonce){
    return annonceRepository.save(annonce);
  }
  public List<Annonce> getAllAnnonce(){
    return annonceRepository.findAll();
  }
}
