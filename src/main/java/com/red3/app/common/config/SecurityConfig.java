package com.red3.app.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.red3.app.common.config.ajax.AjaxAuthFailHandler;
import com.red3.app.common.config.ajax.AjaxAuthSuccessHandler;
import com.red3.app.common.config.ajax.UnauthorizedEntryPoint;
import com.red3.app.common.impl.UserDetailsServiceImpl;
import com.red3.app.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: com.red3.app.common.config.SecurityConfig
 * @description: TODO
 * @author: zxl
 * @create: 2021-04-30 0:26
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(CorsConfigurationSource())
                .and()
                .exceptionHandling().authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and()
                .csrf().disable()

                .authorizeRequests()
                .antMatchers("/login","/all/**").permitAll()//所有用户都可以访问以/user开头的URL
                .antMatchers("/user/**").hasRole("USER")//当用户拥有USER权限，才能访问/user开头的URL
                .anyRequest().authenticated()
                .and()
                .formLogin()

                .loginProcessingUrl("/login")
                .usernameParameter("name")
                .passwordParameter("password")
                .successHandler(new AjaxAuthSuccessHandler())
                .failureHandler(new AjaxAuthFailHandler())
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll();
//                .authorizeRequests()
//                .antMatchers("/all/**").permitAll()//所有用户都可以访问以/user开头的URL
//                .antMatchers("/user/**").hasRole("USER")//当用户拥有USER权限，才能访问/user开头的URL
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/all/login")
//                .usernameParameter("name")
//                .passwordParameter("password1")
//                .successHandler(new SimpleUrlAuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                        httpServletResponse.setContentType("application/json;charset=utf-8");
//                        ServletOutputStream out = httpServletResponse.getOutputStream();
//                        ObjectMapper objectMapper = new ObjectMapper();
//                        objectMapper.writeValue(out, R.ok());
//                        out.flush();
//                        out.close();
//                    }
//                })
//                .failureHandler(new SimpleUrlAuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//                        httpServletResponse.setContentType("application/json;charset=utf-8");
//                        ServletOutputStream out = httpServletResponse.getOutputStream();
//                        ObjectMapper objectMapper = new ObjectMapper();
//                        objectMapper.writeValue(out, R.ok("登录失败"));
//                        out.flush();
//                        out.close();
//                    }
//                })
//                .permitAll()
//                .and()
//                .httpBasic();//允许http提交
    }

    private CorsConfigurationSource CorsConfigurationSource() {
        CorsConfigurationSource source =   new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");	//同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
        corsConfiguration.addAllowedHeader("*");//header，允许哪些header，本案中使用的是token，此处可将*替换为token；
        corsConfiguration.addAllowedMethod("*");	//允许的请求方法，PSOT、GET等
        ((UrlBasedCorsConfigurationSource) source).registerCorsConfiguration("/**",corsConfiguration); //配置允许跨域访问的url
        return source;
    }
}
