package com.example.layered_architecture_demo.Security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
  private final UserDetailsService uds;
  public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService uds) {
    this.jwtUtils = jwtUtils;
    this.uds = uds;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req,
                                  HttpServletResponse res,
                                  FilterChain chain)
      throws ServletException, IOException {
    String header = req.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer ")) {
      String token = header.substring(7);
      if (jwtUtils.validateToken(token)) {
        String username = jwtUtils.getUsernameFromToken(token);
        UserDetails ud = uds.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken auth =
          new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    }
    chain.doFilter(req, res);
  }
}
