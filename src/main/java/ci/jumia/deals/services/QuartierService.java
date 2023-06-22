package ci.jumia.deals.services;

import ci.jumia.deals.entities.Quartier;
import ci.jumia.deals.entities.Ville;
import ci.jumia.deals.repositories.QuartierRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuartierService {
  @Autowired
  QuartierRepository quartierRepository;

  public List<Quartier> QuartiersParVille(String idVille){
    return quartierRepository.findAllByVille(idVille);
  }
}
