package es.limit.auth.concept.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurerComposite;

//@Configuration
//@EnableWebFlux
public class CorsConfig {

    @Bean
    public WebFluxConfigurer corsConfigurer() {

        return new WebFluxConfigurerComposite() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .exposedHeaders("Access-Control-Allow-Origin",
                                "Access-Control-Allow-Methods",
                                "Access-Control-Allow-Headers",
                                "Access-Control-Max-Age",
                                "Access-Control-Request-Headers",
                                "Access-Control-Request-Method");
            }
        };
    }
}
