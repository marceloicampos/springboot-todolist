package br.com.marceloicampos.todolist.filter;

import br.com.marceloicampos.todolist.user.UserRepository;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

  @Autowired
  private UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    var servletPath = request.getServletPath();

    if (servletPath.equals("/tasks/")) {
      // captura o auth, valida user, valida password e segue com o processo
      var authorization = request.getHeader("Authorization");
      System.out.println("Auth: " + authorization);
      var authEncoded = authorization.substring("Basic".length()).trim();
      byte[] authDecoded = Base64.getDecoder().decode(authEncoded);
      var authString = new String(authDecoded);
      // marcelocampos, abc123
      String[] credentials = authString.split(":");
      String username = credentials[0];
      String password = credentials[1];
      System.out.println("authEncoded: " + authEncoded);
      System.out.println("authDecoded: " + authDecoded);
      System.out.println("authString: " + authString);
      System.out.println("username: " + username);
      System.out.println("password: " + password);
      // validar usuário
      var user = this.userRepository.findByUsername(username);
      if (user == null) {
        response.sendError(401, "User Not Found");
      } else {
        // validar a senha
        var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        if (passwordVerify.verified) {
          // segue processo
          request.setAttribute("idUser", user.getId());
          filterChain.doFilter(request, response);
        } else {
          response.sendError(401, "Invalid Password");
        }
      }
    } else {
      filterChain.doFilter(request, response);
    }
  }
}
