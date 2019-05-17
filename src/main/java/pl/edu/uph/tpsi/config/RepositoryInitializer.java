package pl.edu.uph.tpsi.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.repositories.DiscRepository;

import java.util.Arrays;

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
                                discRepository.save(
                                        Disc.builder()
                                                .amount( 100 )
                                                .band( "The beatles" )
                                                .title( "With the Beatles" )
                                                .deleted( false )
                                                .price( 100f )
                                                .images( Arrays.asList( "https://is2-ssl.mzstatic.com/image/thumb/Music/a1/43/f1/mzi.jellenxl.tif/600x600bf.png",
                                                        "http://beatles.ncf.ca/beatlemania.jpe" ) )
                                                .description( "With The Beatles – drugi brytyjski album zespołu The Beatles, nagrany cztery miesiące od pierwszego - Please Please Me.\n" +
                                                        "\n" +
                                                        "Album zawiera osiem kompozycji napisanych przez Beatlesów (siedem duetu Lennon/McCartney i jedna stworzona przez George’a Harrisona) i sześć coverów. Płyta w Wielkiej Brytanii sprzedała się w liczbie ponad miliona kopii i stała się numerem jeden brytyjskich list przebojów, spychając poprzedni album Beatlesów na dalsze miejsce." )
                                                .build()
                                );
                                discRepository.save(
                                        Disc.builder()
                                                .amount( 55 )
                                                .band( "Michael Jackson" )
                                                .title( "Thriller" )
                                                .deleted( false )
                                                .price( 100f )
                                                .images( Arrays.asList( "https://image.ceneostatic.pl/data/products/2722250/i-michael-jackson-thriller.jpg",
                                                        "https://images-na.ssl-images-amazon.com/images/I/51CHlJubDqL.jpg" ) )
                                                .description("Thriller – szósty solowy album studyjny amerykańskiego piosenkarza Michaela Jacksona, wydany 30 listopada 1982 przez wytwórnię Epic Records, nagrywany w Westlake Recording Studios w Los Angeles. Thriller to najlepiej sprzedający się album wszech czasów.")
                                                .build()
                                );
                                discRepository.save(
                                        Disc.builder()
                                                .amount( 100 )
                                                .band( "Queen" )
                                                .title( "A kind of magic" )
                                                .deleted( false )
                                                .price( 100f )
                                                .images( Arrays.asList( "https://images-na.ssl-images-amazon.com/images/I/61p75HL83bL.jpg",
                                                        "https://apollo-ireland.akamaized.net/v1/files/vtceltups9wi2-PL/image;s=644x461" ) )
                                               .description( "A Kind of Magic – album brytyjskiego zespołu rockowego Queen, wydany w 1986 roku.\n" +
                                                       "\n" +
                                                       "Album został wykorzystany jako ścieżka dźwiękowa filmu Nieśmiertelny, w którym pojawiły się (w nieco innych niż na albumie wersjach) utwory: „Princes of the Universe”, „Gimme the Prize”, „Who Wants to Live Forever”, „A Kind of Magic”, „One Year of Love”. W filmie pojawia się też utwór „Hammer to Fall” z poprzedniego albumu." )
                                                .build()
                                );
                        }
                };
        }
}
