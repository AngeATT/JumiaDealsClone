package ci.jumia.deals.entities.user;

import ci.jumia.deals.security.ERoles;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
@Setter
@Getter
@Document("Annonceur")
public class UtilisateurEntity implements UserDetails {
  @NotBlank(message = "Nom obligatoire")
  @Pattern(regexp = "[a-zA-Z ]*")
  String nom;
  @Id
  @Field("_id")
  @Email(message = "format d'email invalide")
  String email;
  @NotBlank(message = "Mot de passe obligatoire")
      @Min(value = 3,message = "Mot de passe trop court")
      @Max(value = 16, message = "Mot de passe trop long")
  String password;
  @NotEmpty(message = "aucun numéro de telephone entré")
  Map<@Pattern(regexp="(^$|[0-9]{10})") String,@AssertFalse Boolean> numeros;
  boolean isConfirme = false;
  boolean isActive = false;

  public UtilisateurEntity(String nom, String email, String password, Map<String,Boolean> numeros, boolean isconfirme, boolean isActive ){
    this.nom = nom;
    this.email = email;
    this.password = password;
    this.numeros = numeros;
    this.isConfirme = isconfirme;
    this.isActive = isActive;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays.asList(new SimpleGrantedAuthority(ERoles.ROLE_ANNONCEUR.toString()));
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
