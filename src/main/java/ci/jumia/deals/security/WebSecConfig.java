package ci.jumia.deals.security;

import ci.jumia.deals.security.jwt.AuthTokenFilter;
import ci.jumia.deals.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;




@Configuration
@EnableMethodSecurity
public class WebSecConfig {
  @Autowired
  CorsConfigurationSource configurationSource;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  UtilisateurService utilisateurService;

  @Autowired
  private AuthenticationEntryPoint unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }


  @Autowired
  DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(utilisateurService);
    authProvider.setPasswordEncoder(passwordEncoder);

    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf(crsf -> crsf.disable())
        .authorizeHttpRequests(auth ->
            auth.requestMatchers("/api/auth/*").permitAll()
                .anyRequest().authenticated());

    httpSecurity.authenticationProvider(authenticationProvider());

    httpSecurity.addFilterBefore(authenticationJwtTokenFilter(),
        UsernamePasswordAuthenticationFilter.class);
    return httpSecurity.build();
  }

}
