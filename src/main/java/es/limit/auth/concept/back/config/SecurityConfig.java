package es.limit.auth.concept.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig { // extends WebSecurityConfigurerAdapter {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/").hasAuthority("LIM_USER")
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
//                .jwtAuthenticationConverter(grantedAuthoritiesExtractor())
                .jwtAuthenticationConverter(new CustomJwtGrantedAuthoritiesConverter())
                .and().and()
                .build();
    }

//    ReactiveJwtAuthenticationConverterAdapter grantedAuthoritiesExtractor() {
//        GrantedAuthoritiesExtractor extractor = new GrantedAuthoritiesExtractor();
//        return new ReactiveJwtAuthenticationConverterAdapter(extractor);
//    }
//
//    static class GrantedAuthoritiesExtractor extends JwtAuthenticationConverter {
//
//        protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
//            Map<String, Object> realmAccess = jwt.getClaim("realm_access");
//            Collection<String> realmRoles = null;
//            if (realmAccess != null &&
//                    (realmRoles = (Collection<String>) realmAccess.get("roles")) != null)
//                return realmRoles.stream()
//                        .map(rol -> new SimpleGrantedAuthority(rol))
//                        .collect(Collectors.toSet());
//            return Collections.emptySet();
//        }
//    }

}
