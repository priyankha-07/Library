package libraryManagement.library.securityconfig;

import jakarta.servlet.Filter;
import libraryManagement.library.filter.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//package libraryManagement.library.securityconfig;
//import libraryManagement.library.filter.JwtAuthFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter authFilter;

    @Bean
    public UserDetailsService userDetailsService() {
//        UserDetails admin = User
//                .withUsername("admin")
//                .password(encoder.encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User
//                .withUsername("user")
//                .password(encoder.encode("user"))
//                .roles("USER")
 //               .build();

  //      return new InMemoryUserDetailsManager(admin, user);
        return new UserInfoUserDetails();
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
public SecurityFilterChain  securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf().disable()
            //.httpBasic()
           // .and()
            // .authorizeRequests(authorize -> authorize
            .authorizeHttpRequests()
            //requestMatchers(HttpMethod.POST, "/library/admin/","/library/user/").authenticated()
            //.requestMatchers("/admin/**").hasRole("ADMIN")// Restrict access to /admin/** to users with role ADMIN
           // .requestMatchers(HttpMethod.GET, "/user/**","/admin/**").hasRole("user, admin") // Allow only GET requests for /user/** to users with role USER
            .requestMatchers("/library/Welcome","/library/authenticate").permitAll()
            .anyRequest().authenticated()
            .and()
            //.oauth2Login(); // Use form-based
            //.formLogin();
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
            .build();

    //return http.build();
}
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
}
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return authenticationProvider;
    }
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


