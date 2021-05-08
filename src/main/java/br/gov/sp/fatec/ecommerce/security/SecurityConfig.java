package br.gov.sp.fatec.ecommerce.security;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


//WebSecurityConfigurerAdapter -> Possui as config web e segurança do spring pré configuradas
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //habilitar segurança por anotação -> onde anotar está seguro
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().httpBasic().and()        
        //this disables session creation on Spring Security
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    
}
