package daiku.app.security;

import daiku.app.app.service.GoenUserDetailsService;
import daiku.domain.infra.entity.GoenUserDetails;
import daiku.domain.infra.entity.TAccounts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class OAuth2Filter extends OncePerRequestFilter {
    @Autowired
    private GoenUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        SecurityContext context = SecurityContextHolder.getContext();

        if(Objects.nonNull(context.getAuthentication()) && context.getAuthentication().getPrincipal() instanceof Jwt) {

            UserDetails user = null;
            String uid = ((Jwt) context.getAuthentication().getPrincipal()).getClaimAsString("user_id");
            String email = ((Jwt) context.getAuthentication().getPrincipal()).getClaimAsString("email");
            if(!request.getRequestURI().equals("/api/account/create")) {
                user = userDetailsService.loadUserByUsername(uid);
            } else {
                var account = new TAccounts();
                account.setUid(uid);
                account.setEmail(email);
                user = new GoenUserDetails(uid, account, AuthorityUtils.createAuthorityList("CREATE_ACCOUNT"));
            }

            var authRequest = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            context.setAuthentication(authRequest);
        }

        filterChain.doFilter(request, response);

    }
}
