package ci.jumia.deals.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Annonceur")
public class Annonceur {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  String annonceurId;
  @NotBlank(message = "Nom obligatoire")
  @Pattern(regexp = "[a-zA-Z ]*")
  String nom;
  @NotBlank(message = "Prenom obligatoire")
      @Min(value = 2, message = "Entrez un prenom d'au moins 2 lettres")
      @Max(value = 50, message = "maximum de caractères dépassé")
  @Pattern(regexp = "[a-zA-Z ]*")
  String prenom;
  @Email(message = "format d'email invalide")
  String email;
  @NotBlank(message = "Mot de passe obligatoire")
      @Min(value = 3,message = "Mot de passe trop court")
      @Max(value = 16, message = "Mot de passe trop long")
  String password;
  @NotEmpty(message = "aucun numéro de telephone entré")
  Map<@Pattern(regexp="(^$|[0-9]{10})") String,@AssertFalse Boolean> telephones;
  @AssertFalse
  boolean isConfirme;

}
