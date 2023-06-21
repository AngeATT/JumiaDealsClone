package ci.jumia.deals.entities;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Annonce")


public class Annonce {
  @Id
  String annonceId;
  String titre;
  String description;
  int prix;
  String[] photos;
  String dateCreation;
  String sousCategorie;
  String ville;

  int telephone;
  boolean isWha;

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

  public String[] getPhotos() {
    return photos;
  }

  public void setPhotos(String[] photos) {
    this.photos = photos;
  }

  public String getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(String dateCreation) {
    this.dateCreation = dateCreation;
  }

  public String getSousCategorie() {
    return sousCategorie;
  }

  public void setSousCategorie(String sousCategorie) {
    this.sousCategorie = sousCategorie;
  }

  public String getVille() {
    return ville;
  }

  public void setVille(String ville) {
    this.ville = ville;
  }

  public int getTelephone() {
    return telephone;
  }

  public void setTelephone(int telephone) {
    this.telephone = telephone;
  }

  public boolean isWha() {
    return isWha;
  }

  public void setWha(boolean wha) {
    isWha = wha;
  }
}
