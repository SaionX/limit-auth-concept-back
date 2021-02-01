package es.limit.auth.concept.back.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class HelloController {

    @CrossOrigin
    @GetMapping()
    public Mono<String> get(@AuthenticationPrincipal Jwt jwt) {
        return Mono.just("{\"msg\": \"Limit Auth Concept WORKING\"}");
    }

}

