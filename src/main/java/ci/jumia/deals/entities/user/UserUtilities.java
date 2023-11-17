package ci.jumia.deals.entities.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Attoungbre Ange François 2023-11-17
 */
@Entity
@Getter
@Setter
public class UserUtilities {
    @Id
    private UUID id;
    private String token;
    private LocalDate dateValidite;
    @OneToOne
    private UtilisateurEntity utilisateur;
}
