package pawie.w.javie.spring.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;

    // Relaja wiele do wielu - uzytkownik ma wiele objektów objekt może należec do wielu użytkowników
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_objects_mapping",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "object_id")
    )
    private List<ObjectEntity> objects = new ArrayList<>();

}