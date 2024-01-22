package com.medico.app.service;

import com.medico.app.domain.Storage;
import com.medico.app.repository.StorageRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class StorageService {
    private static final Logger LOGGER = LogManager.getLogger(StorageService.class);
    private final StorageRepository storageRepository;

    @Value("${folder-path}")
    private String folderPath;

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    @Transactional
    public Storage uploadFile(MultipartFile file) throws IOException {
        String name = new SimpleDateFormat("yyyyMMdd").format(new Date())+ "_"  + UUID.randomUUID() + "_" + file.getOriginalFilename().trim().replace(" ","_");
        String imagePath= folderPath + name;

        Storage storageFile = Storage
                .builder()
                .name(name)
                .path(imagePath)
                .build();

        file.transferTo(new File(imagePath));
        LOGGER.info("file uploaded successfully {} : ", imagePath);
        return storageFile;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<Storage> fileData = storageRepository.findByName(fileName);
        if (fileData.isPresent()){
            String filePath=fileData.get().getPath();
            return Files.readAllBytes(new File(filePath).toPath());
        }
        return new byte[0];
    }

    private Storage ajoutFile(Storage image){
        Optional<Storage> storageOptional = storageRepository.findByName(image.getName());
        storageOptional.ifPresent(storageRepository::delete);
        return storageRepository.save(image);
    }


}
