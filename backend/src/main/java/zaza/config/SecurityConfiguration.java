package zaza.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.savedrequest.NullRequestCache;
import zaza.model.Role;

import javax.sql.DataSource;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/session").permitAll()
                .antMatchers(HttpMethod.GET, "/api/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/orderItems/**").hasRole(Role.MANUFACTURER.toString())
                .antMatchers(HttpMethod.POST, "/api/products").hasAnyRole(Role.MANUFACTURER.toString())
                .antMatchers(HttpMethod.POST, "/api/order/createtestorder").hasAnyRole(Role.ADMIN.toString(), Role.MANUFACTURER.toString())
                .antMatchers(HttpMethod.PUT, "/api/products/**").hasAnyRole(Role.ADMIN.toString(), Role.MANUFACTURER.toString())
                .antMatchers(HttpMethod.PUT, "/api/**").hasRole(Role.ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/api/**").hasRole(Role.ADMIN.toString())
                .and()
                .requestCache()
                .requestCache(new NullRequestCache())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String authoritiesQuery = "" +
                "select u.username, concat('ROLE_', ur.role) as authority " +
                "from User u, User_roles ur  " +
                "where u.id = ur.User_id " +
                "and u.username = ?";
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, true from User where username=?")
                .authoritiesByUsernameQuery(authoritiesQuery);
    }
}
