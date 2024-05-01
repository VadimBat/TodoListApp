package com.goit.todolist.note.auth;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData userData = getByIdOrNull(username);
        if (userData == null) {
            throw new UsernameNotFoundException(username);
        }

        UserDetails result = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Arrays.stream(userData.getAuthority().split(","))
                        .map(it -> (GrantedAuthority) () -> it)
                        .collect(Collectors.toList());
            }

            @Override
            public String getPassword() {
                return userData.getPassword();
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };

        System.out.println("Creating user");
        System.out.println("result.getUsername() = " + result.getUsername());
        System.out.println("result.getPassword() = " + result.getPassword());

        return result;
    }

    private UserData getByIdOrNull(String email) {
        String sql = "SELECT password, authority FROM \"user\" WHERE email = :email";
        return jdbcTemplate.queryForObject(
                sql,
                Map.of("email", email),
                new UserDataMapper()
        );
    }

    private class UserDataMapper implements RowMapper<UserData> {
        @Override
        public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
            return UserData.builder()
                    .password(rs.getString("password"))
                    .authority(rs.getString("authority"))
                    .build();
        }
    }

    @Builder
    @Data
    private static class UserData {
        private String password;
        private String authority;
        /*

INSERT INTO "user" (email, password, authority)
VALUES ('user@mail.com', 'user_password', 'USER'),
       ('admin@mail.com', 'admin_password', 'ADMIN');

         */
    }
}
