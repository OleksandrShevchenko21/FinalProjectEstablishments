package com.finalprojectestablishments.finalprojectestablishments.configs.filters;

import com.finalprojectestablishments.finalprojectestablishments.dao.UserDao;
import com.finalprojectestablishments.finalprojectestablishments.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

public class CustomFilter extends OncePerRequestFilter {

    private UserDao userDao;

    public CustomFilter(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {


        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {


            String token = authorization.replace("Bearer ", "");

            String subject = Jwts.parser()
                    .setSigningKey("gfl".getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
//            String role = (String) Jwts.parser()
//                    .setSigningKey("gfl".getBytes(StandardCharsets.UTF_8))
//                    .parseClaimsJws(token)
//                    .getBody()
//                    .get("role");

            System.out.println("subject: " + subject);
//            System.out.println("role: " + role);

            User userByUsername = userDao.findUserByUserName(subject);
            System.out.println(userByUsername);
            if (userByUsername != null) {
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                userByUsername.getUserName(),
                                userByUsername.getPassword(),
                                Collections.singletonList(new SimpleGrantedAuthority(userByUsername.getRole()))
                        )
                );
            }
        }

        filterChain.doFilter(request, response);

    }


}
