package bj.csam.playlist.PlayManager.Dto;

// ============= CategoryDTO.java =============


import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private int linksCount;
    private List<PlaylistDto> links = new ArrayList<>();
}
