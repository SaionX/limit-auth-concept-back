package es.limit.auth.concept.back.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CustomJwtGrantedAuthoritiesConverter implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {

    private final JwtGrantedAuthoritiesConverter defaultGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    private static Collection<? extends GrantedAuthority> extractResourceRoles(final Jwt jwt) //, final String resourceId)
    {
        Collection<GrantedAuthority> authorities = new ArrayList<>(); //super.extractAuthorities(jwt);
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        Map<String, Object> resource = null;
        Collection<String> realmRoles = null;
        if (realmAccess != null &&
                (realmRoles = (Collection<String>) realmAccess.get("roles")) != null)
            return realmRoles.stream()
                    .map(rol -> new SimpleGrantedAuthority(rol))
                    .collect(Collectors.toSet());
        return Collections.emptySet();
    }

    @Override
    public Mono<AbstractAuthenticationToken> convert(final Jwt jwt)
    {
        Collection<GrantedAuthority> authorities = Stream.concat(
                defaultGrantedAuthoritiesConverter.convert(jwt).stream(),
                extractResourceRoles(jwt).stream()
        ).collect(Collectors.toSet());
        return Mono.just(new JwtAuthenticationToken(jwt, authorities));
    }

//    @Override
//    public <U> Converter<Jwt, U> andThen(Converter<Jwt super AbstractAuthenticationToken, ? extends U> after) {
//        return Mono.just(jwt);
//    }
}
