package ci.jumia.deals.entities;

import jakarta.persistence.Entity;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Document("Annonceur")
public class UtilisateurEntity implements UserDetails {
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

  @AssertFalse
  boolean isActive;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return isActive;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return isActive;
  }

  @Override
  public boolean isEnabled() {
    return isConfirme;
  }
}
