package org.ogin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @author ogin
 */
@Configuration
@ComponentScan(basePackages = {"org.ogin.security", "org.ogin.services", "org.ogin.dao"})
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("tokenRepositoryDao")
    private PersistentTokenRepository tokenRepository;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        BCryptPasswordEncoder pe = new  BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http
                .authorizeRequests()
                .antMatchers("/login-form")
                .anonymous()
                .and()
                .formLogin()
                .loginPage("/user-login")
               .defaultSuccessUrl("/list", true) // the second parameter is for enforcing this url always
                .loginProcessingUrl("/login")
                .failureUrl("/user-login")
                .permitAll();
        http.csrf().disable();*/


     /*http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .and().formLogin().loginPage("/user-login")
                .loginProcessingUrl("/login")
                .failureUrl("/user-login")
                .usernameParameter("ssoId").passwordParameter("password")
             .permitAll()
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/Access_Denied");
*/

//        http.formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .failureUrl("/login?login_error=true")
//                .loginProcessingUrl("/authenticate").usernameParameter("j_username").passwordParameter("j_password");
//
//        http.logout()
//                .logoutUrl("/j_spring_security_logout")
//                .logoutSuccessUrl("/login")
//                .invalidateHttpSession(true)
//                .permitAll();
//
//        http.csrf().disable();
//
//        http.exceptionHandling().accessDeniedPage("/denied");
//
//        http.authorizeRequests().antMatchers("/home**").authenticated();
//        http.authorizeRequests().antMatchers("/vista/*/table.htm", "/vista/*/report/*").access("hasAnyRole('ROLE_Administrator','ROLE_Empleado')");
//        http.authorizeRequests().antMatchers("/rest/*/delete.htm", "/rest/*/update.htm").access("hasRole('ROLE_Administrator')");
//

        http.authorizeRequests().antMatchers("/", "/list")
                .access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
                .antMatchers("/newuser/**", "/delete-user-*").access("hasRole('ADMIN')").antMatchers("/edit-user-*")
                .access("hasRole('ADMIN') or hasRole('DBA')").antMatchers("/user-login")
                .anonymous()
                .and().formLogin().loginPage("/user-login")
                .loginProcessingUrl("/login").usernameParameter("ssoId").passwordParameter("password").permitAll()
                .and()
                .rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
                .tokenValiditySeconds(86400)
                .and().csrf();
//                .and().exceptionHandling().accessDeniedPage("/Access_Denied");



    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
        PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices("remember-me", userDetailsService, tokenRepository);
        return tokenBasedservice;
    }
    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("sunil").password("pass123").roles("USER")
                .and()
                .withUser("admin").password("pass123").roles("ADMIN");
    }*/
}
