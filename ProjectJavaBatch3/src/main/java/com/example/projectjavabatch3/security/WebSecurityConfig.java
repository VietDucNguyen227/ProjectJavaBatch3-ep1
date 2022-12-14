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
                //JWT
//        http.cors().and().csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests().antMatchers("/api/auth/**").permitAll();
//        http.addFilterBefore(authenticationJwtTokenFilter(),
//                UsernamePasswordAuthenticationFilter.class);
        
        http.csrf().disable();

        // C??c trang kh??ng y??u c???u login
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


        // Trang /userInfo y??u c???u ph???i login v???i vai tr?? ROLE_USER ho???c ROLE_ADMIN.
        // N???u ch??a login, n?? s??? redirect t???i trang /login.
        http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

        // Trang ch??? d??nh cho ADMIN
        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");

        // Khi ng?????i d??ng ???? login, v???i vai tr?? XX.
        // Nh??ng truy c???p v??o trang y??u c???u vai tr?? YY,
        // Ngo???i l??? AccessDeniedException s??? n??m ra.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // C???u h??nh cho Login Form.
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
