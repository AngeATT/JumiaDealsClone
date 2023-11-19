package ci.jumia.deals.repositories;

import ci.jumia.deals.entities.user.UserUtilities;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Attoungbre Ange François 2023-11-17
 */
@Repository
public interface UserUtilitiesRepository extends MongoRepository<UserUtilities, UUID> {
    Optional<UserUtilities> findByToken(String token);
}
