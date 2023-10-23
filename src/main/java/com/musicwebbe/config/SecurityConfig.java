package com.musicwebbe.config;


import com.musicwebbe.jwt.CustomAccessDeniedHandler;
import com.musicwebbe.jwt.JwtAuthenticationTokenFilter;
import com.musicwebbe.jwt.RestAuthenticationEntryPoint;
import com.musicwebbe.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountService accountService;

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(new BCryptPasswordEncoder(10));
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*"); // Cho phép tất cả các origin (cần điều chỉnh cho môi trường sản xuất)
        configuration.addAllowedMethod("*"); // Cho phép tất cả các phương thức HTTP (GET, POST, PUT, DELETE, v.v.)
        configuration.addAllowedHeader("*"); // Cho phép tất cả các header

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    protected void configure(HttpSecurity http) throws Exception {
        // Disable crsf cho đường dẫn /api/**
        http.csrf().ignoringAntMatchers("/api/**");
        http.httpBasic().authenticationEntryPoint(restServicesEntryPoint());
        http.authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/apiAccount/**").permitAll()
                .antMatchers("/genres").permitAll()
                .antMatchers("/songs/**").permitAll()
                .antMatchers("/comments/**").permitAll()
                .antMatchers("/playlistLikes/**").permitAll()
                .antMatchers("/songs/getByGenresID/**").permitAll()
                .antMatchers("/playlist/**").permitAll()
                .antMatchers("/apiAccount/auth/**").permitAll()
                // add test -----------
                .antMatchers(HttpMethod.GET, "/songs/**").permitAll()
                .antMatchers(HttpMethod.POST, "/songs/**").permitAll()
                .antMatchers(HttpMethod.GET, "/admin/**").permitAll()
                // add test ----------
                .antMatchers("/songs/**").permitAll() //Test
                .antMatchers("/likes/**").permitAll() //Test
                .antMatchers(HttpMethod.GET, "/api/candies/**", "/api/categories/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/candies/**", "/api/categories/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/candies/**", "/api/categories/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/candies/**", "/api/categories/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/apiAccount/save**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/candies/**", "/api/categories/**","/songs/add").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/apiAccount/save**","/comments/add").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and().csrf().disable();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
    }
}
