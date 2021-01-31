package es.limit.auth.concept.back.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

    @GetMapping("/")
    public Mono<String> get(@AuthenticationPrincipal Jwt jwt) {
        return Mono.just("Limit Auth Concept WORKING");
    }

}

