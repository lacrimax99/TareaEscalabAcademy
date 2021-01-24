package com.escalab.academyappbackend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import com.escalab.academyappbackend.exception.AuthException;

@Configuration //PAra q inicie la app spring la pueda tomar
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
	private ResourceServerTokenServices tokenServices;
	
	@Value("${security.jwt.resource-ids}")
	private String resourceIds;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(resourceIds).tokenServices(tokenServices);
	}



	//Permite excluir o incluir urls q qeuremos exponer a nivel publico
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().authenticationEntryPoint(new AuthException())
		.and()
		.requestMatchers()
		.and()
		.authorizeRequests()
				.antMatchers("/swagger.ui.html/**").authenticated()
				.antMatchers("/alumno/**").authenticated()
//				.antMatchers("/profesor/**").authenticated()
				.antMatchers("/curso/**").authenticated()
				.antMatchers("/matricula/**").authenticated()
				.antMatchers("/tokens/**").permitAll()
				.antMatchers("/usuarios/").permitAll()
				.antMatchers("/usuarios/getAll/**").hasRole("USER");
		
		
	}

	
}
