package pawie.w.javie.spring.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pawie.w.javie.spring.models.UserEntity;
import pawie.w.javie.spring.repositories.UserRepository;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserEntity> findAll() { return userRepository.findAll(); }

    public void save(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono"));
    }

    public void delete(Long id) { userRepository.deleteById(id); }
}