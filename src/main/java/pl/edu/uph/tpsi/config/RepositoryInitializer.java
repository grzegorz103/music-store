package pl.edu.uph.tpsi.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.edu.uph.tpsi.models.*;
import pl.edu.uph.tpsi.repositories.*;

import java.time.LocalDate;
import java.util.*;

@Configuration
public class RepositoryInitializer {
    private final DiscRepository discRepository;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder encoder;

    private final CartRepository cartRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public RepositoryInitializer(DiscRepository discRepository,
                                 UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 BCryptPasswordEncoder encoder,
                                 CartRepository cartRepository,
                                 CategoryRepository categoryRepository) {
        this.discRepository = discRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.cartRepository = cartRepository;
        this.categoryRepository = categoryRepository;
    }

    @Bean
    public InitializingBean initializingBean() {
        return () -> {
            if (categoryRepository.findAll().isEmpty()) {
                categoryRepository.save(Category.builder().name("Country").deleted(false).build());
                categoryRepository.save(Category.builder().name("Disco Polo").deleted(false).build());
                categoryRepository.save(Category.builder().name("Jazz").deleted(false).build());
                categoryRepository.save(Category.builder().name("Metal").deleted(false).build());
                categoryRepository.save(Category.builder().name("Pop").deleted(false).build());
                categoryRepository.save(Category.builder().name("Hip hop").deleted(false).build());
                categoryRepository.save(Category.builder().name("Rock").deleted(false).build());
                categoryRepository.save(Category.builder().name("Techno").deleted(false).build());
                categoryRepository.save(Category.builder().name("Inne").deleted(false).build());
            }

            if (roleRepository.findAll().isEmpty()) {
                roleRepository.save(new UserRole(1L, UserRole.UserType.ROLE_USER));
                roleRepository.save(new UserRole(2L, UserRole.UserType.ROLE_ADMIN));
            }

            if (userRepository.findAll().isEmpty()) {
                User admin = User.builder()
                        .username("admin")
                        .password(encoder.encode("admin"))
                        .address(new Address())
                        .email("admin@admin.pl")
                        .enabled(true)
                        .locked(false)
                        .userRoles(new HashSet<>(Arrays.asList(roleRepository.findUserRoleByUserType(UserRole.UserType.ROLE_ADMIN), roleRepository.findUserRoleByUserType(UserRole.UserType.ROLE_USER))))
                        .build();
                userRepository.save(admin);

                User user = User.builder()
                        .username("user")
                        .password(encoder.encode("user"))
                        .address(new Address())
                        .email("user@user.pl")
                        .enabled(true)
                        .locked(false)
                        .userRoles(new HashSet<>(Collections.singletonList(roleRepository.findUserRoleByUserType(UserRole.UserType.ROLE_USER))))
                        .build();
                userRepository.save(user);

                cartRepository.save(new Cart(1L, admin, new ArrayList<>()));
                cartRepository.save(new Cart(2L, user, new ArrayList<>()));
            }
            if (discRepository.findAll().isEmpty()) {
                discRepository.save(
                        Disc.builder()
                                .amount(100)
                                .band("The beatles")
                                .title("With the Beatles")
                                .deleted(false)
                                .releaseDate(LocalDate.of(1992, 12, 15))
                                .category(categoryRepository.findByName("Pop"))
                                .price(100f)
                                .images(Arrays.asList("https://is2-ssl.mzstatic.com/image/thumb/Music/a1/43/f1/mzi.jellenxl.tif/600x600bf.png",
                                        "http://beatles.ncf.ca/beatlemania.jpe"))
                                .description("With The Beatles – drugi brytyjski album zespołu The Beatles, nagrany cztery miesiące od pierwszego - Please Please Me.\n" +
                                        "\n" +
                                        "Album zawiera osiem kompozycji napisanych przez Beatlesów (siedem duetu Lennon/McCartney i jedna stworzona przez George’a Harrisona) i sześć coverów. Płyta w Wielkiej Brytanii sprzedała się w liczbie ponad miliona kopii i stała się numerem jeden brytyjskich list przebojów, spychając poprzedni album Beatlesów na dalsze miejsce.")
                                .build()
                );
                discRepository.save(
                        Disc.builder()
                                .amount(55)
                                .band("Michael Jackson")
                                .title("Thriller")
                                .deleted(false)
                                .releaseDate(LocalDate.of(1995, 5, 5))
                                .price(100f)
                                .category(categoryRepository.findByName("Pop"))
                                .images(Arrays.asList("https://image.ceneostatic.pl/data/products/2722250/i-michael-jackson-thriller.jpg",
                                        "https://images-na.ssl-images-amazon.com/images/I/51CHlJubDqL.jpg"))
                                .description("Thriller – szósty solowy album studyjny amerykańskiego piosenkarza Michaela Jacksona, wydany 30 listopada 1982 przez wytwórnię Epic Records, nagrywany w Westlake Recording Studios w Los Angeles. Thriller to najlepiej sprzedający się album wszech czasów.")
                                .build()
                );
                discRepository.save(
                        Disc.builder()
                                .amount(100)
                                .band("Queen")
                                .title("A kind of magic")
                                .deleted(false)
                                .releaseDate(LocalDate.of(1999, 12, 12))
                                .price(100f)
                                .category(categoryRepository.findByName("Rock"))
                                .images(Arrays.asList("https://images-na.ssl-images-amazon.com/images/I/61p75HL83bL.jpg",
                                        "https://apollo-ireland.akamaized.net/v1/files/vtceltups9wi2-PL/image;s=644x461"))
                                .description("A Kind of Magic – album brytyjskiego zespołu rockowego Queen, wydany w 1986 roku.\n" +
                                        "\n" +
                                        "Album został wykorzystany jako ścieżka dźwiękowa filmu Nieśmiertelny, w którym pojawiły się (w nieco innych niż na albumie wersjach) utwory: „Princes of the Universe”, „Gimme the Prize”, „Who Wants to Live Forever”, „A Kind of Magic”, „One Year of Love”. W filmie pojawia się też utwór „Hammer to Fall” z poprzedniego albumu.")
                                .build()
                );
            }
        };
    }
}
