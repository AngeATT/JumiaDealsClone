package ci.jumia.deals.entities.mappers;

import ci.jumia.deals.dto.UserDto;
import ci.jumia.deals.entities.user.UtilisateurEntity;
import ci.jumia.deals.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapper {

  @Autowired
  UtilisateurService utilisateurService;

  UtilisateurEntity userDtoToUtilisateurEntity(UserDto userDto) {
    return
        utilisateurService.findByEmail(userDto.getEmail());
  }

}
