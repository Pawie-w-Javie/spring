package pawie.w.javie.spring.services;


import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import pawie.w.javie.spring.models.UserEntity;
import pawie.w.javie.spring.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("Nie ma takiego użytkownika!");

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}