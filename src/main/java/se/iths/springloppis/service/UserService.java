package se.iths.springloppis.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.springloppis.entity.RoleEntity;
import se.iths.springloppis.entity.UserEntity;
import se.iths.springloppis.repository.RoleRepository;
import se.iths.springloppis.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity createUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        RoleEntity roleToAdd = roleRepository.findByRole("ROLE_USER");
        userEntity.addRole(roleToAdd);
        return userRepository.save(userEntity);
    }

    public void deleteUser(Long id) {
        UserEntity foundUser = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        userRepository.deleteById(foundUser.getId());
    }

    public Optional<UserEntity> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Iterable<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

}
