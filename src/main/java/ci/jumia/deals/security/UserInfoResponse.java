package ci.jumia.deals.security;

import jakarta.validation.constraints.Email;
import java.util.List;

/**
 * <p></p>
 *
 * @author Attoungbre Ange François 2023-07-07
 */
public record UserInfoResponse(@Email(message = "format d'email invalide") String email,
                               List<String> authorities) {

}
