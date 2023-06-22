package ci.jumia.deals.rest;

import ci.jumia.deals.entities.Ville;
import ci.jumia.deals.services.VilleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/villes")
public class VilleController {
  @Autowired
  VilleService villeService;
  @GetMapping(produces = "application/json")
  List<Ville> getALlVilles(){
    return villeService.getAllVilles();
  }
}
