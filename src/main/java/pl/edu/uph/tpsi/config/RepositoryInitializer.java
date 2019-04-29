package pl.edu.uph.tpsi.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.repositories.DiscRepository;

@Configuration
public class RepositoryInitializer
{
        private final DiscRepository discRepository;

        @Autowired
        public RepositoryInitializer ( DiscRepository discRepository )
        {
                this.discRepository = discRepository;
        }

        @Bean
        public InitializingBean initializingBean ()
        {
                return () -> {
                        if ( discRepository.findAll().isEmpty() )
                        {
                                discRepository.save( Disc.builder().amount( 100 ).band( "The beatles" ).deleted( false ).price( 100f ).build() );
                                discRepository.save( Disc.builder().amount( 55 ).band( "Michael Jackson" ).deleted( false ).price( 100f ).build() );
                                discRepository.save( Disc.builder().amount( 100 ).band( "Queen" ).deleted( false ).price( 100f ).build() );
                        }
                };
        }
}
