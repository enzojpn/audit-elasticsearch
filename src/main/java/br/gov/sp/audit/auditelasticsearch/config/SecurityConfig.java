package br.gov.sp.audit.auditelasticsearch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		  http.authorizeRequests()
          // Configuração das permissões
          .antMatchers("/webjars/**", "/css/**", "/image/**", "/js/**").permitAll() 
          .anyRequest().authenticated()
          .and()
              .formLogin()
              .loginPage("/login").defaultSuccessUrl("/audit", true)
              .permitAll()
              .failureUrl("/login?error=true") // Página de login personalizada para erros
          .and()
              .logout()
              .logoutSuccessUrl("/login")
          .and()
              .csrf().disable(); 
		
//		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login")
//				.defaultSuccessUrl("/audit", true)
//				
//				 .failureUrl("/login?error=true")
//				.and().logout()
//				.logoutSuccessUrl("/login").permitAll();

	}
 
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/css/**", "/image/**", "/js/**"); 
    }

	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("usuario") // Usuário para autenticação
				.password("{noop}senha") // Senha (use {noop} para indicar que é uma senha sem codificação)
				.roles("USER");
	} 
}
