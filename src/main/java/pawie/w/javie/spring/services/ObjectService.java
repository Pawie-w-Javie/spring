package pawie.w.javie.spring.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pawie.w.javie.spring.models.ObjectEntity;
import pawie.w.javie.spring.models.UserEntity;
import pawie.w.javie.spring.repositories.ObjectRepository;
import pawie.w.javie.spring.repositories.UserRepository;

import jakarta.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ObjectService {

    private final ObjectRepository objectRepository;
    private final UserRepository userRepository;

    public ObjectService(ObjectRepository objectRepository, UserRepository userRepository) {
        this.objectRepository = objectRepository;
        this.userRepository = userRepository;
    }

    // Pbieranie listy
    @Transactional
    public List<ObjectEntity> getObjectsForUser(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user != null) {
            // Wymusza załadowanie kolekcji obiektów przed zamknięciem transakcji
            user.getObjects().size();
            return user.getObjects();
        }
        return List.of();
    }

    @Transactional
    public void saveObject(MultipartFile file, String username) throws IOException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null || file.isEmpty()) return;

        ObjectEntity objectEntity = new ObjectEntity();
        objectEntity.setName(file.getOriginalFilename());
        objectEntity.setContentType(file.getContentType());
        objectEntity.setCreatedAt(LocalDateTime.now());
        objectEntity.setData(file.getBytes());

        // powiązanie w obie strony
        objectEntity.getOwners().add(user);
        objectRepository.save(objectEntity);

        user.getObjects().add(objectEntity);
        userRepository.save(user);
    }


    public ObjectEntity getObjectById(Long id) {
        return objectRepository.findById(id).orElse(null);
    }

    // udostepnienie plik innemu uztytkownikowi
    @Transactional
    public boolean shareWithUser(Long objectId, String targetUsername) {
        ObjectEntity object = objectRepository.findById(objectId).orElse(null);
        UserEntity targetUser = userRepository.findByUsername(targetUsername);

        if (object != null && targetUser != null) {
            if (!targetUser.getObjects().contains(object)) {
                targetUser.getObjects().add(object);
                userRepository.save(targetUser);
                return true;
            }
        }
        return false;
    }
}