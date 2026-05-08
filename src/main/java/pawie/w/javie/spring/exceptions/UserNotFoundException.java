package pawie.w.javie.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // spring automatycznie zwróci 404
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Użytkownik o ID:" + id + " nie istnieje.");
    }
}