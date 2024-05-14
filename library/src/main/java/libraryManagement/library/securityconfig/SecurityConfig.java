package libraryManagement.library.securityconfig;//package libraryManagement.library.securityconfig;
//
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
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserInfoUserDetails();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable()
//                .httpBasic()
//                .and()
//
//
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/library/Welcome", "/library/authenticate").permitAll()
//                        // .requestMatchers( "/hospital/addDoctorsInfo").hasRole("ADMIN")
////                        .requestMatchers(HttpMethod.GET, "/library/display/**").hasRole("ADMIN")
////                        .requestMatchers(HttpMethod.GET, "/library/display/AllAdmins").hasRole("ADMIN")
////                        .requestMatchers(HttpMethod.GET, "/library/display/AllUsers").hasAnyRole("ADMIN", "USER")
//
//
//                        .requestMatchers(HttpMethod.POST, "/library/add/**").hasRole("ADMIN")
////                        .requestMatchers(HttpMethod.PUT, "/library/update/**").hasRole("ADMIN")
////                        .requestMatchers(HttpMethod.DELETE, "/library/delete/**").hasRole("ADMIN")
//
//                )
//                .formLogin()
//                .and()
//                .build();
//
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration){
//        return
//    }
//
//}
//

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    //@Autowired
   // private JwtAuthFilter authFilter;
@Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return
//        http.csrf().disable()
//
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//                .requestMatchers("/library/welcome", "/library/add/DetailsOfUser").permitAll()
//                .requestMatchers( HttpMethod.POST,"/library/authenticate/**").hasRole("ADMIN")
//                .and()
//                .formLogin()
//                .and()
//                .build();
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/library/authenticate").permitAll()
            .and()
            //.authorizeHttpRequests().requestMatchers("/library/**").hasAnyRole("ADMIN","USER")
           // .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider())
           // .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
}


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}
