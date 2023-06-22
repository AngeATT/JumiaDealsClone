package ci.jumia.deals.services;

import ci.jumia.deals.entities.Ville;
import ci.jumia.deals.repositories.VilleRepository;
import ci.jumia.deals.rest.VilleController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VilleService {
  @Autowired
  VilleRepository villeRepository;
  public List<Ville> getAllVilles(){
    return villeRepository.findAll();
  }
}
