package bj.csam.playlist.PlayManager.Dto;

// ============= PlaylistLinkDTO.java =============


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PlaylistDto {
    private Long id;
    private String title;
    private String url;
    private String platform;
    private Long categoryId;
    private String categoryName;
    private LocalDateTime createdAt;
}