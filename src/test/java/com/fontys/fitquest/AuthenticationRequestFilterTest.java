package com.fontys.fitquest;

import com.fontys.fitquest.business.exception.InvalidAccessTokenException;
import com.fontys.fitquest.configuration.auth.AuthenticationRequestFilter;
import com.fontys.fitquest.configuration.token.AccessToken;
import com.fontys.fitquest.configuration.token.AccessTokenDecoder;
import com.fontys.fitquest.configuration.token.impl.AccessTokenImpl;
import com.fontys.fitquest.domain.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;

import java.lang.reflect.Method;

import static org.mockito.Mockito.*;

class AuthenticationRequestFilterTest {

    @Mock
    private AccessTokenDecoder accessTokenDecoder;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain chain;

    @InjectMocks
    private AuthenticationRequestFilter filter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();
    }

    @Test
    void doFilterInternal_validToken() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer validToken");
        AccessToken accessToken = new AccessTokenImpl("email@example.com", 1L, Role.USER);
        when(accessTokenDecoder.decode("validToken")).thenReturn(accessToken);

        Method method = AuthenticationRequestFilter.class.getDeclaredMethod("doFilterInternal", HttpServletRequest.class, HttpServletResponse.class, FilterChain.class);
        method.setAccessible(true);
        method.invoke(filter, request, response, chain);

        verify(chain, times(1)).doFilter(request, response);
    }

    @Test
    void doFilterInternal_invalidToken() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer invalidToken");
        when(accessTokenDecoder.decode("invalidToken")).thenThrow(new InvalidAccessTokenException("Invalid token"));

        Method method = AuthenticationRequestFilter.class.getDeclaredMethod("doFilterInternal", HttpServletRequest.class, HttpServletResponse.class, FilterChain.class);
        method.setAccessible(true);
        method.invoke(filter, request, response, chain);

        verify(response, times(1)).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        verify(response, times(1)).flushBuffer();
        verify(chain, times(0)).doFilter(request, response);
    }

    @Test
    void doFilterInternal_noToken() throws Exception {
        when(request.getHeader("Authorization")).thenReturn(null);

        Method method = AuthenticationRequestFilter.class.getDeclaredMethod("doFilterInternal", HttpServletRequest.class, HttpServletResponse.class, FilterChain.class);
        method.setAccessible(true);
        method.invoke(filter, request, response, chain);

        verify(chain, times(1)).doFilter(request, response);
    }
}