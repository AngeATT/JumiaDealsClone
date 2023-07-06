package ci.jumia.deals.security;

import ci.jumia.deals.entities.UtilisateurEntity;
import java.util.Map;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * classe dto pour l'enregistrement des utilisateurs
 */
@Data
public class FormulaireEnregistrement {
  String nom;
  String email;
  String numero;
  String password;
  Map<String, Boolean> numeros;
  //TODO : valider les comptes manuellement après
  public UtilisateurEntity toUtlisateur(PasswordEncoder passwordEncoder){
      return new UtilisateurEntity(this.nom,this.email,passwordEncoder.encode(this.password),this.numeros,true,true);
  }
}
