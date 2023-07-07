package ci.jumia.deals;

/**
 * <p></p>
 *
 * @author Attoungbre Ange François 2023-07-07
 */
public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(){
    System.out.println("Utilisateur non trouve");
  }
}
