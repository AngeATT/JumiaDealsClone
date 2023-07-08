package ci.jumia.deals;

/**
 * <p></p>
 *
 * @author Attoungbre Ange François 2023-07-08
 */
public class ErrorMessageException extends RuntimeException{
  public ErrorMessageException(String messageErreur){
    System.out.println(messageErreur);
  }
}
