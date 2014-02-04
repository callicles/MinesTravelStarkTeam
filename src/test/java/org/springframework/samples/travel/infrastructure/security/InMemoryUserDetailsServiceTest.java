package org.springframework.samples.travel.infrastructure.security;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class InMemoryUserDetailsServiceTest {

    private final InMemoryUserDetailsService service = new InMemoryUserDetailsService();

    @Test
    public void shouldFindKeithUserName() {
        // Given
        String username = "keith";

        // When
        UserDetails details = service.loadUserByUsername(username);

        // Then
        assertThat(details.getUsername()).isEqualTo("keith");
        assertThat(details.getPassword()).isEqualTo("417c7382b16c395bc25b5da1398cf076");
        assertThat(details.getAuthorities()).onProperty("authority").contains("ROLE_USER", "ROLE_SUPERVISOR");
    }

    @Test
    public void shouldFindErwinUserName() {
        // Given
        String username = "erwin";

        // When
        UserDetails details = service.loadUserByUsername(username);

        // Then
        assertThat(details.getUsername()).isEqualTo("erwin");
        assertThat(details.getPassword()).isEqualTo("12430911a8af075c6f41c6976af22b09");
        assertThat(details.getAuthorities()).onProperty("authority").contains("ROLE_USER", "ROLE_SUPERVISOR");
    }

    @Test
    public void shouldFindJeremyUserName() {
        // Given
        String username = "jeremy";

        // When
        UserDetails details = service.loadUserByUsername(username);

        // Then
        assertThat(details.getUsername()).isEqualTo("jeremy");
        assertThat(details.getPassword()).isEqualTo("57c6cbff0d421449be820763f03139eb");
        assertThat(details.getAuthorities()).onProperty("authority").contains("ROLE_USER");
    }

    @Test
    public void shouldFindScottUserName() {
        // Given
        String username = "scott";

        // When
        UserDetails details = service.loadUserByUsername(username);

        // Then
        assertThat(details.getUsername()).isEqualTo("scott");
        assertThat(details.getPassword()).isEqualTo("942f2339bf50796de535a384f0d1af3e");
        assertThat(details.getAuthorities()).onProperty("authority").contains("ROLE_USER");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void shouldThrowExceptionIfUsernameNotFound() {
        // Given
        String username = "scotty";

        // When & Then
        service.loadUserByUsername(username);
    }
}
