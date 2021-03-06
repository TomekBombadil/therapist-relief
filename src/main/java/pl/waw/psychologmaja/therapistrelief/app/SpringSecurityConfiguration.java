package pl.waw.psychologmaja.therapistrelief.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.waw.psychologmaja.therapistrelief.repository.UserRepository;
import pl.waw.psychologmaja.therapistrelief.service.CustomUserDetailsService;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@EnableWebSecurity
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean entityManagerFactoryBean
                = new LocalEntityManagerFactoryBean();
        entityManagerFactoryBean.setPersistenceUnitName("TherapistReliefPersistenceUnit");
        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager =
                new JpaTransactionManager(entityManagerFactory);
        return jpaTransactionManager;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().accessDeniedPage("/auth/403page")
                .and()
                .authorizeRequests()
                .antMatchers("/user/*").hasAuthority("ADMIN")
                .antMatchers("/patient/*").hasAuthority("USER")
                .antMatchers("/session/*").hasAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))//to musia??em da?? zamiast logoutUrl, ??eby dzia??a?? request GET + CSRF
                .clearAuthentication(true).logoutSuccessUrl("/auth/login").invalidateHttpSession(true)
                .and()
                .formLogin()
                .loginPage("/auth/login").usernameParameter("email").passwordParameter("password")//??eby logowa?? si?? emailem a nie username
                .defaultSuccessUrl("/session/upcoming", true)
                .permitAll();       // strona logowanie musi by?? dost??na dla wszystkich
    }
}
