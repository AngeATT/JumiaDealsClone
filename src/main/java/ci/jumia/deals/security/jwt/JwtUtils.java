package ci.jumia.deals.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import java.security.Key;
import io.jsonwebtoken.security.Keys;
import ci.jumia.deals.entities.UtilisateurEntity;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

/**
 * <p>
 *   Classe utilitaire pour les tokens jwt
 * </p>
 *
 * @author Attoungbre Ange François 2023-07-10
 */
@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
  @Value("${deals.app.jwtSecret}")
  private String jwtSecret;

  @Value("${deals.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  @Value("${deals.app.jwtCookieName}")
  private String jwtCookie;

  /**
   * Récupération du cookie qui a le nom spécifié
   * @param request
   * @return
   */
  
  public String getJwtFromCookies(HttpServletRequest request){
    Cookie cookie = WebUtils.getCookie(request,jwtCookie);
    if (cookie != null){
      return cookie.getValue();
    }else{
      return null;
    }
  }

  public ResponseCookie generateJwtCookie(UtilisateurEntity utilisateur){
    String jwt = generateTokenFromEmail(utilisateur.getEmail());
    return
        ResponseCookie.from(jwtCookie,jwt).path("/api").maxAge(24*60*60)
            .httpOnly(true).build();
  }

  public ResponseCookie getCleanJwtCookie(){
      return ResponseCookie.from(jwtCookie,"").path("/api").build();
  }

  public String getEmailFromJwtToken(String token){
    return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJwt(token)
        .getBody().getSubject();
  }

  private Key key(){
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  public boolean validateJwtToken(String authToken){
    try {
      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
      return true;
    }catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token est expiré: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token non supporté: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string est vide: {}", e.getMessage());
    }
    return false;
  }

  public String generateTokenFromEmail(String email){
    return Jwts.builder()
        .setSubject(email)
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date().getTime() + jwtExpirationMs)))
        .signWith(key(), SignatureAlgorithm.HS256)
        .compact();

  }

}
