package bj.csam.playlist.PlayManager.Service;

import bj.csam.playlist.PlayManager.Model.Image;
import bj.csam.playlist.PlayManager.Repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final Path uploadPath = Paths.get("uploads/images");

    public Image save(MultipartFile file) throws IOException {
        return save(file, null, null);
    }

    public Image save(MultipartFile file, String title, String description) throws IOException {
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(filename);
        Files.copy(file.getInputStream(), filePath);

        Image image = new Image();
        image.setFilename(filename);
        image.setOriginalFilename(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setPath(filePath.toString());
        image.setTitle(title);
        image.setDescription(description);

        return imageRepository.save(image);
    }

    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    public Image findById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        Image image = findById(id);
        if (image != null) {
            try {
                Files.deleteIfExists(Paths.get(image.getPath()));
            } catch (IOException e) {
                // Log error
            }
            imageRepository.deleteById(id);
        }
    }
}