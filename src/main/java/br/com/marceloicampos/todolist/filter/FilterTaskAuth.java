package br.com.marceloicampos.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    // captura o auth, valida user, valida password e segue com o processo
    var authorization = request.getHeader("Authorization");
    System.out.println("Auth: " + authorization);
    var authEncoded = authorization.substring("Basic".length()).trim();
    byte[] authDecoded = Base64.getDecoder().decode(authEncoded);
    var authString = new String(authDecoded);
    String[] credentials = authString.split(":");
    String username = credentials[0];
    String password = credentials[1];

    System.out.println("authEncoded: " + authEncoded);
    System.out.println("authDecoded: " + authDecoded);
    System.out.println("authString: " + authString);
    System.out.println("username: " + username);
    System.out.println("password: " + password);
    filterChain.doFilter(request, response);
  }
}
