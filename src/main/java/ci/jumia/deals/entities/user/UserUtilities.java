package ci.jumia.deals.entities.user;

import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Attoungbre Ange François 2023-11-17
 */
@Document("UserUtilities")
@Getter
@Setter
public class UserUtilities {
    @Id
    private UUID id;
    @NotBlank
    private String token;
    private LocalDate dateValidite = LocalDate.now();
    @OneToOne
    private UtilisateurEntity utilisateur;
}
