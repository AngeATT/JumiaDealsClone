package ci.jumia.deals.entities;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Quartier")
public class Quartier {
  @Id
  String idQuartier;
  @NotBlank
  String libelleQuartier;
  String idVille;

}
