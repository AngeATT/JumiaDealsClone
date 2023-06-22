package ci.jumia.deals.entities;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class Ville {
  @Id
  String idVille;
  @NotBlank
  String villeLibelle;

}
