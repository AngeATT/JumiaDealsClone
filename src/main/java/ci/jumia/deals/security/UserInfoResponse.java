package ci.jumia.deals.security;

import jakarta.validation.constraints.Email;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p></p>
 *
 * @author Attoungbre Ange François 2023-07-07
 */
public record UserInfoResponse(String annonceurId,
                               @Email(message = "format d'email invalide") String email,
                               Collection<? extends GrantedAuthority> authorities) {
}
