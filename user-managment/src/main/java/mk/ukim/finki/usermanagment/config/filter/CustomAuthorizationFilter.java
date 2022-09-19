package mk.ukim.finki.usermanagment.config.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mk.ukim.finki.usermanagment.config.CustomAuthenticationToken;
import mk.ukim.finki.usermanagment.config.CustomUserDetailsService;
import mk.ukim.finki.usermanagment.service.form.LoggedUser;
import mk.ukim.finki.usermanagment.util.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Log4j2
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {

        try {
            if (request
                    .getServletPath()
                    .startsWith(String.format("%s/", "/public"))) {
                filterChain.doFilter(request, response);
                return;
            }

            String authorizationHeader = request.getHeader(JwtUtils.AUTH_HEADER);
            if (authorizationHeader == null) {
                filterChain.doFilter(request, response);
                return;
            }

            if (!authorizationHeader.startsWith(JwtUtils.TOKEN_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = authorizationHeader.replace(JwtUtils.TOKEN_PREFIX, "");

            SecurityContextHolder.getContext().setAuthentication(JwtUtils.getFrom(token));
            UsernamePasswordAuthenticationToken authToken = JwtUtils.getFrom(token);
            LoggedUser loggedUserDto =
                    (LoggedUser)
                            customUserDetailsService.loadUserByUsername(authToken.getPrincipal().toString());
            CustomAuthenticationToken customAuthenticationToken =
                    new CustomAuthenticationToken(loggedUserDto, loggedUserDto.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(customAuthenticationToken);

            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            log.error(ex);

            List<String> messages = new ArrayList<>();
            Throwable currentException = ex;
            while (currentException != null && currentException != currentException.getCause()) {
                messages.add(currentException.getLocalizedMessage());
                currentException = currentException.getCause();
            }

            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            String jsonBody = new ObjectMapper().writeValueAsString(messages);
            response.getWriter().write(jsonBody);
            response.flushBuffer();
        }
    }
}