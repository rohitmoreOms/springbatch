package com.example.passwordencoding.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.Serializable;
import java.util.Date;
import java.util.stream.Collectors;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.passwordencoding.Controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Component
    public static class TokenProvider implements Serializable {


        private static final String SECRET = "demo_project";
        private static final long EXPIRATION_TIME = 864_000_000; // 10 days

        public String generateToken(String username) {
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();
        }

        public String extractUsername(String token) {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }

        private static final String AUTHORITIES_KEY ="scopes" ;
        private static final String SIGNING_KEY = "";
        private static final long ACCESS_TOKEN_VALIDITY_SECONDS =  8 * 60 * 60;

        public String generateToken(Authentication authentication) {
            final String authorities = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                     .collect(Collectors.joining(","));
            //SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            //  .signWith(SignatureAlgorithm.HS512, SIGNING_KEY);
            return Jwts.builder()
                    .setSubject(authentication.getName())
                    .claim(AUTHORITIES_KEY, authorities)
                    .signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                    .compact();
        }

    }
}

/*
    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("PhilmCGI  Admin")
                .description("PhilmCGI  API Documentation")
                .version("1.0").contact(new Contact("OMSOFTWARE PVT LTD", "https://www.omsoftware.net/", "info@omsoftware.net"))
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey(HttpHeaders.AUTHORIZATION, "Authorization", "header");
    }

 */

