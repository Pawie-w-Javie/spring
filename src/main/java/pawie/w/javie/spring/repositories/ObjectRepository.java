package pawie.w.javie.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pawie.w.javie.spring.models.ObjectEntity;

public interface ObjectRepository extends JpaRepository<ObjectEntity, Long> {
}