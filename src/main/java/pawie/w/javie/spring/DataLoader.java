package pawie.w.javie.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pawie.w.javie.spring.models.UserEntity;
import pawie.w.javie.spring.repositories.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public DataLoader(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {

        UserEntity u = new UserEntity(null, "user", encoder.encode("123"), "USER");
        repo.save(u);


        UserEntity a = new UserEntity(null, "admin", encoder.encode("admin"), "ADMIN");
        repo.save(a);
    }
}