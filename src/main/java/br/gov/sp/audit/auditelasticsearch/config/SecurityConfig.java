package br.gov.sp.audit.auditelasticsearch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http


        // Configuração do logout
        .logout()
            .logoutUrl("/logout") // URL para efetuar o logout
            .logoutSuccessUrl("/login?logout") // URL para redirecionar após o logout bem-sucedido
            .invalidateHttpSession(true) // Invalida a sessão do usuário
            .deleteCookies("JSESSIONID") // Exclui os cookies relacionados à sessão
        .and()
        // Configuração de autorização
        .authorizeRequests()
            .antMatchers("/login", "/logout" , "/audit" , "/list" ,"/pessoa" ).permitAll() // Permite acesso à página de login e logout
            .anyRequest().authenticated() // 
		
		.and()
			.formLogin()
			.defaultSuccessUrl("/audit", true); 
	}

}
