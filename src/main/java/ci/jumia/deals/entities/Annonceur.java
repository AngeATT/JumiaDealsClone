package ci.jumia.deals.entities;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Annonceur")
public class Annonceur {
  @Id
  String annonceurId;
  String nom;
  String prenom;
  String email;
  String password;
  int telephone;
  boolean isWha;
  boolean estValide;

}
