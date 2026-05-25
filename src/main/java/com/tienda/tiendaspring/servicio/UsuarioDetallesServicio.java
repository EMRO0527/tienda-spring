package com.tienda.tiendaspring.servicio;

import com.tienda.tiendaspring.modelo.Usuario;
import com.tienda.tiendaspring.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioDetallesServicio implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioDetallesServicio.class);
    private final UsuarioRepositorio repositorio;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        logger.info("Buscando usuario: {}", username);
        Usuario usuario = repositorio.findByUsername(username);

        if (usuario == null) {
            logger.error("Usuario no encontrado: {}", username);
            throw new UsernameNotFoundException(
                    "No existe el usuario: " + username);
        }

        logger.info("Usuario autenticado: {}", usuario.getUsername());

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(usuario.getRoles().stream()
                        .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                        .collect(Collectors.toList()))
                .disabled(!usuario.isActivo())
                .build();
    }
}