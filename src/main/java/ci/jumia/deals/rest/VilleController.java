package ci.jumia.deals.rest;

import ci.jumia.deals.entities.Quartier;
import ci.jumia.deals.entities.Ville;
import ci.jumia.deals.services.QuartierService;
import ci.jumia.deals.services.VilleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VilleController {
  @Autowired
  VilleService villeService;
  @Autowired
  QuartierService quartierService;
  @GetMapping(path = "/villes",produces = "application/json")
  List<Ville> getALlVilles(){
    return villeService.getAllVilles();
  }
}
