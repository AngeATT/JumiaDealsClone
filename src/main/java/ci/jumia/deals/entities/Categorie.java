package ci.jumia.deals.entities;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Categorie")
public class Categorie {
  @Id
  String idCategorie;
  @NotBlank
  String libelleCategorie;
}
