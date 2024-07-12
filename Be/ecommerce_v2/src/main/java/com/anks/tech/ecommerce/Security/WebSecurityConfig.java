package com.anks.tech.ecommerce.Security;

import com.anks.tech.ecommerce.Security.JWT.AuthEntryPointJwt;
import com.anks.tech.ecommerce.Security.JWT.AuthTokenFilter;
import com.anks.tech.ecommerce.Security.Services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
//@EnableWebSecurity
@EnableMethodSecurity
//(securedEnabled = true,
//jsr250Enabled = true,
//prePostEnabled = true) // by default
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth. requestMatchers(
                                        "/favicon.ico",
                                        "/**/*.png",
                                        "/**/*.gif",
                                        "/**/*.svg",
                                        "/**/*.jpg",
                                        "/swagger-ui/**",
                                        "/api/v1/auth/**",
                                        "/v3/api-docs/**"
                                )
                                .permitAll()

                                .requestMatchers(HttpMethod.GET,
                                        "/api/v1/products/**",
                                        "/api/v1/verify-account",
                                        "/products/files/**"
                                ).permitAll()

                                .requestMatchers(HttpMethod.GET,"/api/v1/ServicesSetting/setting").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/api/v1/regenerate-otp").permitAll()

                                .requestMatchers(HttpMethod.POST,
                                        "/api/v1/register",
                                        "/api/v1/reviews/**",
                                        "/api/v1/customer/new-order")
                                .permitAll()

                                .requestMatchers(HttpMethod.GET,"/api/v1/products/admin/all").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/v1/products/**").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST,"/api/v1/products/**").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/api/v1/products/**").hasAnyAuthority("ADMIN")


                                .requestMatchers(HttpMethod.GET,"/api/v1/reviews/**").permitAll()

                                .requestMatchers(HttpMethod.POST,"/api/v1/ServicesSetting/createSetting").hasAnyAuthority("ADMIN")



                                .requestMatchers(HttpMethod.GET,"/api/v1/customer/**").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/api/v1/customer/**").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/v1/customer/**").hasAnyAuthority("ADMIN")
                                .requestMatchers("/api/v1/accounts/**").hasAnyAuthority("ADMIN")


                                .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

//        http .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .logout().invalidateHttpSession(true).deleteCookies("JSESSIONID");
        return http.build();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }


}
