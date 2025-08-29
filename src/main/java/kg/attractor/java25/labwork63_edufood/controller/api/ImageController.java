package kg.attractor.java25.labwork63_edufood.controller.api;

import kg.attractor.java25.labwork63_edufood.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {
    private final FileService fileService;

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<?> downloadImage(@PathVariable String filename) {
        return fileService.getFile(filename);
    }
}
