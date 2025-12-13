package bj.csam.playlist.PlayManager.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Sound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String originalFilename;
    private String contentType;
    private long size;
    private String path;
    private String eventType; // reminder, notification, etc.
    private String title;
    private String description;
}