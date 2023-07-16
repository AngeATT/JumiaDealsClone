package ci.jumia.deals.security.jwt;

import ci.jumia.deals.services.UtilisateurService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import org.slf4j.Logger;

/**
 * <p>
 *   Filtre qui s'execute une fois par requete.
 *
 * </p>
 *
 * @author Attoungbre Ange François 2023-07-10
 */
public class AuthTokenFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  UtilisateurService utilisateurService;

  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

  /**
   * -récupère JWT depuis les HTTP cookies
   * -Si la requête possède un JWT et elle est valide, elle y extrait le nom de l'utilisateur
   * -récupere l'utilisateur, récupere son rôle, crée un token d'authentification puis ajoute
   * l'utilisateur dans le contexte de sécurité via le setAuthentication
   * Dorénavant on peut récupérer l'utiilisateur avec (UserDetails) SecurityContextHolder.
   * getContext().getAuthentication().getPrincipal();
   *
   * @param request
   * @param response
   * @param filterChain
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
      try{
        String jwt = parseJwt(request);
        if (jwt != null && jwtUtils.validateJwtToken(jwt)){
          String username = jwtUtils.getEmailFromJwtToken(jwt);

          UserDetails userDetails = utilisateurService.loadUserByUsername(username);
          UsernamePasswordAuthenticationToken authenticationToken =
              new UsernamePasswordAuthenticationToken(userDetails,null,
                  userDetails.getAuthorities());

          authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
      }catch (Exception e){
        logger.error("Impossible de faire l'authentification de l'utilisateur");
      }
      filterChain.doFilter(request,response);
  }

  private String parseJwt(HttpServletRequest request){
    return jwtUtils.getJwtFromCookies(request);
  }
}
