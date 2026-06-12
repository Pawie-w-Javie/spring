package pawie.w.javie.spring.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pawie.w.javie.spring.models.ObjectEntity;
import pawie.w.javie.spring.services.ObjectService;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/obiekty")
public class ObjectController {

    private final ObjectService objectService;

    public ObjectController(ObjectService objectService) {
        this.objectService = objectService;
    }


    @GetMapping
    public String listObjects(Model model, Principal principal) {
        model.addAttribute("objects", objectService.getObjectsForUser(principal.getName()));
        return "archiwum";
    }


    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Principal principal) {
        try {
            objectService.saveObject(file, principal.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/moje-obiekty";
    }

    // pobieranie pliku z bazy
    @GetMapping("/download/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        ObjectEntity object = objectService.getObjectById(id);
        if (object == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(object.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + object.getName() + "\"")
                .body(object.getData());
    }

    // Udostępnianie pliku
    @PostMapping("/share")
    public String shareFile(@RequestParam("objectId") Long objectId, @RequestParam("targetUsername") String targetUsername) {
        objectService.shareWithUser(objectId, targetUsername);
        return "redirect:/moje-obiekty";
    }
}