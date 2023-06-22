package ci.jumia.deals.entities;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Ville")
public class Ville {
  @Id
  String idVille;
  @NotBlank
  String villeLibelle;

}
