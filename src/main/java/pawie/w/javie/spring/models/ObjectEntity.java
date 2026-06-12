package pawie.w.javie.spring.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "objects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ObjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contentType;
    private LocalDateTime createdAt;

    @Lob // BYTEA w Postgres
    private byte[] data;            // plik po bitach

    @ManyToMany(mappedBy = "objects")
    private List<UserEntity> owners = new ArrayList<>();
}