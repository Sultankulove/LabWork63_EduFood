package kg.attractor.java25.labwork63_edufood.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String saveFile(MultipartFile file);

    ResponseEntity<?> getFile(String fileName);
}
