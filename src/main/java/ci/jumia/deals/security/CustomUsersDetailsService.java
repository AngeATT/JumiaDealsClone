package ci.jumia.deals.security;

import ci.jumia.deals.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CustomUsersDetailsService implements UserDetailsService {

  @Autowired
  private UtilisateurService utilisateurService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return utilisateurService.findByEmail(email);
  }

}
