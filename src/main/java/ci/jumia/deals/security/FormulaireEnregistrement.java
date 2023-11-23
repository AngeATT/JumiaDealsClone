package ci.jumia.deals.security;

import ci.jumia.deals.entities.user.UtilisateurEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

/**
 * classe dto pour l'enregistrement des utilisateurs
 */
public class FormulaireEnregistrement {
  @NotBlank(message = "Nom obligatoire !")
  private String nom;
  @Email(message = "Email obligatoire !")
  private String email;
  @NotBlank(message = "Mot de passe obligatoire !")
  private String password;
  private Map<String, Boolean> numeros;

  public UtilisateurEntity toUtlisateur(PasswordEncoder passwordEncoder){
      UtilisateurEntity utilisateur = new UtilisateurEntity();
      utilisateur.setNom(this.nom);
      utilisateur.setEmail(this.email);
      utilisateur.setPassword(passwordEncoder.encode(this.password));
      utilisateur.setNumeros(this.numeros);
      return utilisateur;
  }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public Map<String, Boolean> getNumeros() {
        return numeros;
    }
}
