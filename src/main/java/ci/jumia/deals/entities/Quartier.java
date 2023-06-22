package ci.jumia.deals.entities;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Quartier {
  @Id
  String idQuartier;
  @NotBlank
  String libelleQuartier;
  @DBRef
  Ville ville;
}
