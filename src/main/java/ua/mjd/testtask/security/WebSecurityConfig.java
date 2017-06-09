package ua.mjd.testtask.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.mjd.testtask.dao.repositories.AccountRepository;
import ua.mjd.testtask.model.Account;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    AccountRepository accountRepository;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/","/customers/getAll").permitAll()
                .antMatchers("/customer/update").authenticated()
                .antMatchers("/customers/update").hasRole("ADMIN")
                .and()
                .httpBasic()
                .and().logout().logoutUrl("/logout").invalidateHttpSession(true)
                .permitAll()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();


    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
        /*auth
                .inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN").
                and().withUser("user").password("password").roles("USER");*/
    }


    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Account account = accountRepository.findByName(username);
                System.out.println("ACCOUNT"+account.getName());
                if(account != null) {
                    return new User(account.getName(), account.getPassword(), true, true, true, true,
                            AuthorityUtils.createAuthorityList(account.getUserType().toString()));
                } else {
                    throw new UsernameNotFoundException("could not find the user '"
                            + username + "'");
                }
            }

        };
    }
}
