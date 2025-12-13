package bj.csam.playlist.PlayManager.Service;

import bj.csam.playlist.PlayManager.Model.Sound;
import bj.csam.playlist.PlayManager.Repository.SoundRepository;
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
public class SoundService {

    private final SoundRepository soundRepository;
    private final Path uploadPath = Paths.get("uploads/sounds");

    public Sound save(MultipartFile file, String eventType) throws IOException {
        return save(file, null, null, eventType);
    }

    public Sound save(MultipartFile file, String title, String description, String eventType) throws IOException {
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(filename);
        Files.copy(file.getInputStream(), filePath);

        Sound sound = new Sound();
        sound.setFilename(filename);
        sound.setOriginalFilename(file.getOriginalFilename());
        sound.setContentType(file.getContentType());
        sound.setSize(file.getSize());
        sound.setPath(filePath.toString());
        sound.setEventType(eventType);
        sound.setTitle(title);
        sound.setDescription(description);

        return soundRepository.save(sound);
    }

    public List<Sound> findAll() {
        return soundRepository.findAll();
    }

    public List<Sound> findByEventType(String eventType) {
        return soundRepository.findByEventType(eventType);
    }

    public Sound findById(Long id) {
        return soundRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        Sound sound = findById(id);
        if (sound != null) {
            try {
                Files.deleteIfExists(Paths.get(sound.getPath()));
            } catch (IOException e) {
                // Log error
            }
            soundRepository.deleteById(id);
        }
    }

    public void playSound(String eventType) {
        List<Sound> sounds = findByEventType(eventType);
        if (!sounds.isEmpty()) {
            // Logic to play sound, for now just log
            System.out.println("Playing sound for event: " + eventType);
        }
    }
}