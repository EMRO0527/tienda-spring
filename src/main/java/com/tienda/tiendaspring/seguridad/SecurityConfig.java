package com.tienda.tiendaspring.seguridad;

import com.tienda.tiendaspring.servicio.UsuarioDetallesServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UsuarioDetallesServicio usuarioDetallesServicio;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        builder
                .userDetailsService(usuarioDetallesServicio)
                .passwordEncoder(passwordEncoder());
        return builder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .requestMatchers("/api-docs/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/swagger-resources/**").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/favicon.ico").permitAll()
                        .requestMatchers("/productos").permitAll()
                        .requestMatchers("/categorias").permitAll()
                        .requestMatchers("/vista/login").permitAll()
                        .requestMatchers("/vista/acceso-denegado").permitAll()
                        .requestMatchers("/vista/productos").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers("/vista/productos/guardar").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/vista/productos/actualizar/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/vista/productos/eliminar/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/vista/productos/editar/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/vista/login")
                        .loginProcessingUrl("/vista/login")
                        .defaultSuccessUrl("/vista/productos", true)
                        .failureUrl("/vista/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/vista/logout")
                        .logoutSuccessUrl("/vista/login?logout")
                        .permitAll()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api-docs/**")
                        .ignoringRequestMatchers("/swagger-ui/**")
                        .ignoringRequestMatchers("/v3/api-docs/**")
                );

        return http.build();
    }
}