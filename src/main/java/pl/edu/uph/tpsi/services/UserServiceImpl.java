package pl.edu.uph.tpsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pl.edu.uph.tpsi.dto.AddressDTO;
import pl.edu.uph.tpsi.dto.UserDTO;
import pl.edu.uph.tpsi.exceptions.UserExistsException;
import pl.edu.uph.tpsi.exceptions.UserNotExistsException;
import pl.edu.uph.tpsi.mappers.AddressMapper;
import pl.edu.uph.tpsi.mappers.UserMapper;
import pl.edu.uph.tpsi.models.User;
import pl.edu.uph.tpsi.models.UserRole;
import pl.edu.uph.tpsi.repositories.RoleRepository;
import pl.edu.uph.tpsi.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final RoleRepository roleRepository;

    private final CartService cartService;

    private final AddressService addressService;

    private final AddressMapper addressMapper;

    private final UserMapper userMapper;

    @Value("${user.not.exists}")
    private String userNotExists;

    @Value("${user.exists}")
    private String userExists;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder encoder,
                           RoleRepository roleRepository,
                           CartService cartService,
                           AddressService addressService,
                           AddressMapper addressMapper,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
        this.cartService = cartService;
        this.addressService = addressService;
        this.addressMapper = addressMapper;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(s);

        if (user == null)
            throw new UsernameNotFoundException(this.userNotExists);

        return user;
    }

    @Override
    @Transactional
    public User create(UserDTO userDTO) {
        if (getByUsername(userDTO.getUsername()) != null)
            throw new UserExistsException(this.userExists);

        User user = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .enabled(true)
                .password(encoder.encode(userDTO.getPassword()))
                .address(addressService.create(new AddressDTO()))
                .userRoles(Collections.singleton(roleRepository.findUserRoleByUserType(UserRole.UserType.ROLE_USER)))
                .build();
        userRepository.save(user);
        cartService.create(user);
        return user;
    }

    @Override
    public User getByUsername(String username) {
        if (!StringUtils.isEmpty(username)) {
            return userRepository.findUserByUsername(username);
        }
        return null;
    }

    @Override
    public UserDTO getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userMapper.UserToDTO(user);
    }

    @Override
    public boolean isLoginCorrect(String login, String password) {
        User u = userRepository.findUserByUsername(login);
        if (u == null) {
            return false;
        }

        return u.getUsername().equals(login)
                && encoder.matches(password, u.getPassword());
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::UserToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {
        userRepository.findById(id)
                .ifPresent(e -> {
                    e.setEmail(userDTO.getEmail());
                    e.setUsername(userDTO.getUsername());
                    e.setPassword(encoder.encode(userDTO.getPassword()));
                    userRepository.save(e);
                });
        return userRepository.findById(id)
                .map(userMapper::UserToDTO)
                .orElseThrow(() -> new UserNotExistsException(this.userNotExists));
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id).map(e -> {
            e.setLocked(!e.isLocked());
            userRepository.save(e);
            return e;
        }).orElseThrow(() -> new UserNotExistsException(this.userNotExists));
    }

}
