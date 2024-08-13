package com.luisetin.userBase.config;


import com.luisetin.userBase.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Desactivar el csrf para que no lo pida
        http.csrf(customizer -> customizer.disable());

        //Exigir autorizacion en cualquier request de nuestro servidor
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());

        //Aceptar user y pass al hacer peticiones postman
        http.httpBasic(Customizer.withDefaults());

        //Aceptar user y pass en login al abrir web
        http.formLogin(Customizer.withDefaults());

        //Hacer que haya una sesion por peticion ( siempre tener que pasarle el user y pass, que no se guarde )
        // ( no va con el form )
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }

    //Cambiar al AuthenticationProvider para poder autenticar usuarios de la base de datos
    @Bean
    public AuthenticationProvider authenticationProvider() {
        //Clase que implementa AuthenticationProvider
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }

}
