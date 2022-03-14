package com.example.vidT.services.implementation;

import com.example.vidT.models.FileMy;
import com.example.vidT.models.Video;
import com.example.vidT.repositories.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileRepo fileRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public void fileLoader(MultipartFile file,Video post) throws IOException {
        FileMy fileMy = new FileMy();
        if (!file.isEmpty()) {
            File uploadD = new File(uploadPath);
            if (!uploadD.exists()) {
                uploadD.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            fileMy.setFilename(resultFilename);
            fileMy.setVideo(post);
            fileRepo.save(fileMy);
        }
    }


}
