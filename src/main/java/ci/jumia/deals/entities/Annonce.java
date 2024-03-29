package ci.jumia.deals.entities;

import ci.jumia.deals.entities.user.UtilisateurEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Annonce")
public class Annonce {
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  String annonceId;
  @NotBlank(message = "Entrez un titre")
  @Min(value = 3,message = "Entrez un titre d'au moins 3 lettres")
  @Max(value=30, message = "votre titre ne doit pas dépasser 50 caractères")
  String titre;
  @NotBlank(message = "Entrez une description de votre annonce")
  @Min(value = 3,message = "Entrez une description d'au moins 3 lettres")
  @Max(value = 500,message = "votre description ne peut dépasser 500 caractères")
  String description;
  @NotNull
  int prix;
  List<String> photos;
  @NotNull(message = "Date de création non entrée")
  String dateCreation;
  @NotBlank(message = "Categorie non spécifiée")
  Categorie categorie; //Garde pae il y a rechercher par sous categorie
  @NotNull(message = "La sous catégorie est non spécifiée")
  SousCategorie sousCategorie; //pas string car recherche par sous categories et besoin des champs de cette sous categorie
  @NotBlank(message = "La ville est non spécifier")
  Ville ville; //on garde car il y  a la recherche par ville
  String quartier;
  @AssertFalse
  Boolean estValide;
  @AssertFalse
  Boolean estVip;
  String debutVip;
  String finVip;
  @DBRef
      //@NotNull TODO: supprimer ça quand on aura des données des annonceurs
  UtilisateurEntity utilisateur;
  @NotNull
  Map<@Pattern(regexp="(^$|[0-9]{10})") String,@AssertFalse Boolean> numero;

  public void setSousCategorie(SousCategorie sousCategorie) {
    this.sousCategorie = sousCategorie;
  }

  public void setVille(Ville ville) {
    this.ville = ville;
  }
  public Boolean getEstValide() {
    return estValide;
  }

  public void setEstValide(Boolean estValide) {
    this.estValide = estValide;
  }

  public Boolean getEstVip() {
    return estVip;
  }

  public void setEstVip(Boolean estVip) {
    this.estVip = estVip;
  }

  public String getDebutVip() {
    return debutVip;
  }

  public void setDebutVip(String debutVip) {
    this.debutVip = debutVip;
  }

  public String getFinVip() {
    return finVip;
  }

  public void setFinVip(String finVip) {
    this.finVip = finVip;
  }

  public String getTitre() {
    return titre;
  }

  public void setTitre(String titre) {
    this.titre = titre;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getPrix() {
    return prix;
  }

  public void setPrix(int prix) {
    this.prix = prix;
  }
  public String getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(String dateCreation) {
    this.dateCreation = dateCreation;
  }
}
