package pawie.w.javie.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pawie.w.javie.spring.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}