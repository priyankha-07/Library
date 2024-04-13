package libraryManagement.library.securityconfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User
                .withUsername("admin")
                .password(encoder.encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails user = User
                .withUsername("user")
                .password(encoder.encode("user"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                // .authorizeRequests(authorize -> authorize
//                .authorizeHttpRequests().requestMatchers("/admin/**").authenticated()
//                .requestMatchers("/user/**").authenticated()
//                //.requestMatchers("/admin/**").hasRole("ADMIN")// Restrict access to /admin/** to users with role ADMIN
//              //  .requestMatchers(HttpMethod.GET, "/user/**").hasRole("USER") // Allow only GET requests for /user/** to users with role USER
//                .requestMatchers("/library/Welcome").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin(); // Use form-based login
//
//        return http.build();
    //}
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .httpBasic()
            .and()
            // .authorizeRequests(authorize -> authorize
            .authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/admin/").authenticated()
            //.requestMatchers("/admin/**").hasRole("ADMIN")// Restrict access to /admin/** to users with role ADMIN
            .requestMatchers(HttpMethod.GET, "/user/**").hasRole("USER") // Allow only GET requests for /user/** to users with role USER
            .requestMatchers("/library/Welcome").permitAll()
            .anyRequest().authenticated()
            .and()
            .oauth2Login(); // Use form-based login

    return http.build();
}

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}