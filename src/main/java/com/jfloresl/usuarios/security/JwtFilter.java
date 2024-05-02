package com.jfloresl.usuarios.security;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        // 1 validar sear header autorizathion valido

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader==null || authHeader.isEmpty()|| !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

        // 2. validar jwt valido

        String jwt=authHeader.split(" ")[1].trim();

        if(!this.jwtUtil.isValid(jwt)){
            filterChain.doFilter(request,response);//aqui no se cargo nada
            return;
        }

        // 3. validar usuario del UserDeatailsServices
        String email = this.jwtUtil.getEmail(jwt);
        System.out.println("$$$$$   jwt:"+jwt);
        User user= (User) this.userDetailsService.loadUserByUsername(email);
        System.out.println("$$$$$ $$$$$ valores:");
        System.out.println("$$$$$ $$$$$ user.getUsername():"+user.getUsername());
        System.out.println("$$$$$ $$$$$ user.getPassword():"+user.getPassword());
        // 4. cargar el usuario al contecto de seguridad
        UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(
                user.getUsername(),user.getPassword(),user.getAuthorities()
        );

        // cargar al contexto de seguridad


        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println("authenticationToken_:"+authenticationToken);
        filterChain.doFilter(request,response);

    }
}
