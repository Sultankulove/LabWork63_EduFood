package kg.attractor.java25.labwork63_edufood.service.impl;

import kg.attractor.java25.labwork63_edufood.service.FileService;
import kg.attractor.java25.labwork63_edufood.util.FileUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    private static final String BASE_DIR = "images";

    @Override
    public String saveFile(MultipartFile file) {
        return FileUtil.saveUploadedFile(file, BASE_DIR);
    }

    @Override
    public ResponseEntity<?> getFile(String fileName) {
        return FileUtil.downloadImage(fileName, BASE_DIR);
    }
}
