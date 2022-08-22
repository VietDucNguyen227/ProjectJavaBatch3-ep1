package com.example.projectjavabatch3.security;


import com.example.projectjavabatch3.security.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // Các trang không yêu cầu login
        http.authorizeRequests().antMatchers("/", "/login", "/logout",
                "/register", "/registerSubmit").permitAll();
        http.authorizeRequests().antMatchers("/css/*" ).permitAll();
        http.authorizeRequests().antMatchers("/fonts/*" ).permitAll();
        http.authorizeRequests().antMatchers("/scss/*" ).permitAll();
        http.authorizeRequests().antMatchers("/scss/**" ).permitAll();
        http.authorizeRequests().antMatchers("/css/**" ).permitAll();
        http.authorizeRequests().antMatchers("/js/*" ).permitAll();
        http.authorizeRequests().antMatchers("/images/**" ).permitAll();
        http.authorizeRequests().antMatchers("/getimage/**" ).permitAll();
        http.authorizeRequests().antMatchers("**/images/**" ).permitAll();
        http.authorizeRequests().antMatchers("/static/*" ).permitAll();
        http.authorizeRequests().antMatchers("/static/**" ).permitAll();
        http.authorizeRequests().antMatchers("/static/scss/*" ).permitAll();
        http.authorizeRequests().antMatchers("/showItem" ).permitAll();


        // Trang /userInfo yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
        // Nếu chưa login, nó sẽ redirect tới trang /login.
        http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

        // Trang chỉ dành cho ADMIN
        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");

        // Khi người dùng đã login, với vai trò XX.
        // Nhưng truy cập vào trang yêu cầu vai trò YY,
        // Ngoại lệ AccessDeniedException sẽ ném ra.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Cấu hình cho Login Form.
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin()//
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/") ;
    }
}
