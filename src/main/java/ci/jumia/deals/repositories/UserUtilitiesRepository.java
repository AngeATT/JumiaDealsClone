package ci.jumia.deals.repositories;

import ci.jumia.deals.entities.user.UserUtilities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Attoungbre Ange François 2023-11-17
 */
public interface UserUtilitiesRepository extends JpaRepository<UserUtilities, UUID> {
    Optional<UserUtilities> findByToken(String token);
}
