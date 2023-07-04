package ci.jumia.deals.dto;

public class UserDto {

  String email;
  String password;
  boolean isActive;

  UserDto(String email, String password, boolean isActive){
    this.email = email;
    this.password = password;
    this.isActive = isActive;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }
}
