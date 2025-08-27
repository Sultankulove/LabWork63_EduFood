package kg.attractor.java25.labwork63_edufood.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final DataSource dataSource;
    private final ApplicationConfig appConfig;


    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager jdbc = new JdbcUserDetailsManager(dataSource);

        jdbc.setUsersByUsernameQuery("select id, name, enabled from users");

//        jdbc.setAuthoritiesByUsernameQuery("select name ");



        return jdbc;
    }
}
