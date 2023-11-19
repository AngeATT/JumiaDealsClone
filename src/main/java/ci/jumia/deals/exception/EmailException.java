package ci.jumia.deals.exception;

/**
 * @author Attoungbre Ange François 2023-11-19
 */
public class EmailException extends RuntimeException{
    public EmailException(String message){
        super(message);
    }
}
