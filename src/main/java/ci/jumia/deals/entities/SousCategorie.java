package ci.jumia.deals.entities;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("SousCategorie")
public abstract class SousCategorie {
  @Id
  String idSousCategorie;
  @NotBlank
  String libelleSousCategorie;
  @DBRef
  Categorie categorie;

}
