package pawie.w.javie.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/login")
    public String loginPage() {
        return "logowanie";
    }
    @GetMapping("/publiczna")
    public String publicPage() { return "publiczna"; }

    @GetMapping("/prywatna")
    public String privatePage() { return "prywatna"; }

    @GetMapping("/admin")
    public String adminPage() { return "admin"; }
}