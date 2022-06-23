package fr.fms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	DataSource dataSource;
	
	@Override  
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder pe = passwordEncoder();

		// *si* mémoire interne :
//		auth.inMemoryAuthentication().withUser("Mathieu").password(pe.encode("fms2022")).roles("ADMIN","USER");	
//		auth.inMemoryAuthentication().withUser("Eric").password(pe.encode("fms2022")).roles("USER");
//		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());
		
		// *si* BDD :
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select username as principal, password as credentials, active from T_Users where username=?")
		.authoritiesByUsernameQuery("select username as principal, role as role from T_Users_Roles where username=?")
		.rolePrefix("ROLE_")
		.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.formLogin().loginPage("/login");							//   --------------------------> page de login perso
		http.formLogin();												//   --------------------------> page de login generée
	
	// Gestion des accès
		http.authorizeRequests().antMatchers("/index","/articles").permitAll();
		http.authorizeRequests().antMatchers("/cart","/addCart","/removeCart","/article","/delete","/save","/edit","/login","/test").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/cart","/addCart","/removeCart","/login","/test").hasRole("USER");
//		
		http.exceptionHandling().accessDeniedPage("/403");	//au cas ou un utilisateur tente d'accéder à une page non authorisée
	}
}


