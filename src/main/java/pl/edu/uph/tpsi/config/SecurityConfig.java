package pl.edu.uph.tpsi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
        @Override
        protected void configure ( HttpSecurity http ) throws Exception
        {
                http
                        .authorizeRequests()
                        .antMatchers( "/login" ).permitAll()
                        .anyRequest()
                        .permitAll()
                        .and()
                        .httpBasic()
                        .and()
                        .csrf().disable();
        }

        @Bean
        public BCryptPasswordEncoder passwordEncoder ()
        {
                return new BCryptPasswordEncoder();
        }

}
