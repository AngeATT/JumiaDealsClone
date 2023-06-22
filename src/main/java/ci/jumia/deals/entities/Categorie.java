package ci.jumia.deals.entities;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Categorie")
public class Categorie {
  @Id
  String idCategorie;
  @NotBlank
  String libelleCategorie;
  @DBRef
  List<SousCategorie> sousCategories;
}
