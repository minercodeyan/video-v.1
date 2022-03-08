package com.example.vidT.services;

import com.example.vidT.models.Video;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    @Value("${upload.path}")
    private String uploadPath;

    public void fileLoader(MultipartFile file, Video post) throws IOException {
        if (!file.isEmpty()) {
            File uploadD = new File(uploadPath);
            if (!uploadD.exists()) {
                uploadD.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            post.setFilename(resultFilename);
        }
    }


}
