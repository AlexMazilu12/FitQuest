package com.fontys.fitquest.authentication;

import com.fontys.fitquest.configuration.auth.RequestAuthenticatedUserProvider;
import com.fontys.fitquest.configuration.token.AccessToken;
import com.fontys.fitquest.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestAuthenticatedUserProviderTest {

    @InjectMocks
    private RequestAuthenticatedUserProvider provider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAuthenticatedUserInRequest_validToken() {
        SecurityContext context = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        AccessToken accessToken = new AccessToken() {
            @Override
            public String getEmail() {
                return "email@example.com";
            }

            @Override
            public Long getUserId() {
                return 1L;
            }

            @Override
            public Role getRole() {
                return Role.USER;
            }

            @Override
            public Boolean hasRole(String roleName) {
                return Role.USER.name().equals(roleName);
            }
        };

        when(context.getAuthentication()).thenReturn(authentication);
        when(authentication.getDetails()).thenReturn(accessToken);
        SecurityContextHolder.setContext(context);

        AccessToken result = provider.getAuthenticatedUserInRequest();

        assertNotNull(result);
        assertEquals(accessToken, result);
    }

    @Test
    void getAuthenticatedUserInRequest_noAuthentication() {
        SecurityContext context = mock(SecurityContext.class);

        when(context.getAuthentication()).thenReturn(null);
        SecurityContextHolder.setContext(context);

        AccessToken result = provider.getAuthenticatedUserInRequest();

        assertNull(result);
    }

    @Test
    void getAuthenticatedUserInRequest_noDetails() {
        SecurityContext context = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);

        when(context.getAuthentication()).thenReturn(authentication);
        when(authentication.getDetails()).thenReturn(null);
        SecurityContextHolder.setContext(context);

        AccessToken result = provider.getAuthenticatedUserInRequest();

        assertNull(result);
    }

    @Test
    void getAuthenticatedUserInRequest_invalidDetails() {
        SecurityContext context = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);

        when(context.getAuthentication()).thenReturn(authentication);
        when(authentication.getDetails()).thenReturn("InvalidDetails");
        SecurityContextHolder.setContext(context);

        AccessToken result = provider.getAuthenticatedUserInRequest();

        assertNull(result);
    }
}