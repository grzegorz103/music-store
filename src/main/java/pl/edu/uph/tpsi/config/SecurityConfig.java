package pl.edu.uph.tpsi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
        @Override
        protected void configure ( HttpSecurity http ) throws Exception
        {
                http.csrf().disable();
        }

        @Bean
        public WebMvcConfigurerAdapter corsConfigurer ()
        {
                return new WebMvcConfigurerAdapter()
                {
                        @Override
                        public void addCorsMappings ( CorsRegistry registry )
                        {
                                registry.addMapping( "/**" ).allowedOrigins( "http://localhost:4200" );
                        }
                };
        }

        @Bean
        public BCryptPasswordEncoder passwordEncoder ()
        {
                return new BCryptPasswordEncoder();
        }
}
