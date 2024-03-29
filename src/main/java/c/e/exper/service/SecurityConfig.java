package c.e.exper.service;

import c.e.exper.mapper.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   
   final
   CustomDetailService customDetailService;
   
   @Autowired
   PictureMapper pictureMapper;
   
   
   public SecurityConfig(CustomDetailService customDetailService, PictureMapper pictureMapper) {
      this.customDetailService = customDetailService;
      
   }
   
   @Bean
   public static PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
   
   @Override
   public void configure(WebSecurity web) {
      web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
   }
   
   @Override
   public void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
            .antMatchers("/**").hasAnyRole("user", "admin")
            .antMatchers("/**").permitAll()
            .and()
            .formLogin()
            .loginPage("/")
            .loginProcessingUrl("/api/login")
            .successHandler(loginSuccessHandler)
            .failureHandler(loginFailureHandler)
//                .defaultSuccessUrl("/")
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/api/logout")
            .permitAll();
      
      http.csrf().disable();
      
      http.cors();

//        http.logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login")
//                .invalidateHttpSession(true);
      
      http.exceptionHandling()
            .accessDeniedPage("/login");
      
   }
   
   @Override
   public void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(customDetailService);
   }
   
   @Bean
   public CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration();
      
      configuration.addAllowedOriginPattern("*");
      configuration.addAllowedHeader("*");
      configuration.addAllowedMethod("*");
      configuration.setAllowCredentials(true);
      
      
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
   }
   
   AuthenticationSuccessHandler loginSuccessHandler = (request, response, authentication) -> {
      User user = (User) authentication.getPrincipal();
      System.out.println("name" + user.getUsername());
      String picture = pictureMapper.selectPicnameByUserId(user.getUsername());
      System.out.println(picture);
      response.addHeader("photo", picture);
      response.setContentType("application/json");
      response.setCharacterEncoding("utf-8");
      
      //궁금한점
      response.getWriter().write(pictureMapper.selectPicnameByUserId(user.getUsername()));
   };
   
   
   AuthenticationFailureHandler loginFailureHandler = (request, response, exception) -> response.addHeader("gg", "ss");
}
