package com.finalprojectestablishments.finalprojectestablishments.configs;

import com.finalprojectestablishments.finalprojectestablishments.configs.filters.CustomFilter;
import com.finalprojectestablishments.finalprojectestablishments.dao.UserDao;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDao userDao;
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            System.out.println("login trig");
            com.finalprojectestablishments.finalprojectestablishments.entity.User user = userDao.findUserByUserName(username);
            return new User(
                    user.getUserName(),
                    user.getPassword(),
                    Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
        });

    }
    @Bean
    public CustomFilter customFilter(){
        return new CustomFilter(userDao);
    }



    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
        configuration.setAllowedHeaders(Arrays.asList("*"));

        configuration.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.PATCH.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.HEAD.name()
        ));
        configuration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Override
    @Bean

    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/users").permitAll()
                .antMatchers(HttpMethod.GET, "/api/restaurants").permitAll()

                .antMatchers(HttpMethod.POST, "/api/restaurants/admin/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/restaurants/admin/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/restaurants/admin/**").hasAnyRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/api/news/general/admin/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/news/general/admin/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/news/general/admin/**").hasAnyRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/api/news/promotion/admin/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/news/promotion/admin/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/news/promotion/admin/**").hasAnyRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/api/news/event/admin/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/news/event/admin/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/news/event/admin/**").hasAnyRole("ADMIN")

//                .antMatchers(HttpMethod.POST, "/api/reviews/restaurant/{restaurantId}/{userName}").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.POST, "/api/users").permitAll()
//                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                .antMatchers(HttpMethod.GET, "/secure").hasAnyRole("ADMIN", "USER")
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors().configurationSource(corsConfigurationSource())

                .and().addFilterBefore(

                        customFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

}
