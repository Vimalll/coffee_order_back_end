package com.jwt.config;

import com.jwt.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    //vimal
    @Bean
    CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }


    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(corsFilter(), SessionManagementFilter.class); //adds your custom CorsFilter
        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeRequests()
                .antMatchers("/token").permitAll()  // FOR TOKEN API SPRING SECURITY IS DISABLED, no jwt authentication needed
                .antMatchers("/registration").permitAll()   // SAME FOR REGISTRATION api
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }
    // cors handling by vimal
    @Override
    public void configure(final WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.OPTIONS);
    }


    @Bean
    public FilterRegistrationBean processCorsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("'");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);


        final FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter());
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    //over
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }




}

