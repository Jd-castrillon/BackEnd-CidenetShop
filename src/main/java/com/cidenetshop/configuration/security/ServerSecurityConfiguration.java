package com.cidenetshop.configuration.security;

import com.cidenetshop.configuration.security.jwt.JwtEntryPoint;
import com.cidenetshop.configuration.security.jwt.JwtTokenFilter;
import com.cidenetshop.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ServerSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtEntryPoint jwtEntryPoint;

    private final UserDetailServiceImpl userDetailService;

    private final JwtTokenFilter jwtTokenFilter;

    @Autowired
    public ServerSecurityConfiguration(JwtEntryPoint jwtEntryPoint, UserDetailServiceImpl userDetailService,
            JwtTokenFilter jwtTokenFilter) {
        this.jwtEntryPoint = jwtEntryPoint;
        this.userDetailService = userDetailService;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    //	@Bean
//	public JwtTokenFilter jwtTokenFilter() {
//		return new JwtTokenFilter();
//	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // TODO Auto-generated method stub
        return super.authenticationManagerBean();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        // TODO Auto-generated method stub
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.cors();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers( HttpMethod.GET , "/products/**" ).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//
//		http.cors();
//
//		http.csrf().disable();
//
//		http.authorizeRequests().antMatchers("/public/**").permitAll().anyRequest().permitAll();
//	}

}
