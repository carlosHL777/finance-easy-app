package br.com.fatecmaua.finance_easy_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

// HASH DAS SENHAS

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean //permitindo acesso a todos endpoints por enquanto
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	PathPatternRequestMatcher.Builder path = PathPatternRequestMatcher.withDefaults();
    	http
    		.csrf(csrf -> csrf.disable())
    		.headers(h -> h.frameOptions(f -> f.sameOrigin()))
    		.authorizeHttpRequests(auth -> auth
    				.requestMatchers(
    						path.matcher("/cadastrar"),
    						path.matcher("/swagger-ui/***"),
    						path.matcher("/swagger-ui.html"),
    						path.matcher("/v3/api-docs/**"),
    						path.matcher("/h2-console/***"))
    				.permitAll()
    				.anyRequest().authenticated())
    			.formLogin(Customizer.withDefaults())
    			.logout(Customizer.withDefaults());
        return http.build();
    }
    
    
}
