
// ============= PlaylistLinkMapper.java =============
package bj.csam.playlist.PlayManager.Mapper;



import bj.csam.playlist.PlayManager.Dto.PlaylistDto;
import bj.csam.playlist.PlayManager.Model.Playlist;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaylistMapper {

    public PlaylistDto toDTO(Playlist link) {
        if (link == null) return null;

        PlaylistDto dto = new PlaylistDto();
        dto.setId(link.getId());
        dto.setTitle(link.getTitle());
        dto.setUrl(link.getUrl());
        dto.setPlatform(link.getPlatform());
        dto.setCreatedAt(link.getCreatedAt());

        if (link.getCategory() != null) {
            dto.setCategoryId(link.getCategory().getId());
            dto.setCategoryName(link.getCategory().getName());
        }

        return dto;
    }

    public Playlist toEntity(PlaylistDto dto) {
        if (dto == null) return null;

        Playlist link = new Playlist();
        link.setId(dto.getId());
        link.setTitle(dto.getTitle());
        link.setUrl(dto.getUrl());
        link.setPlatform(dto.getPlatform());
        link.setCreatedAt(dto.getCreatedAt());
        return link;
    }

    public List<PlaylistDto> toDTOList(List<Playlist> links) {
        return links.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}