

// ============= PlaylistLinkService.java =============
package bj.csam.playlist.PlayManager.Service;

import bj.csam.playlist.PlayManager.Dto.PlaylistDto;
import bj.csam.playlist.PlayManager.Mapper.PlaylistMapper;
import bj.csam.playlist.PlayManager.Model.Category;
import bj.csam.playlist.PlayManager.Model.Playlist;
import bj.csam.playlist.PlayManager.Repository.CategoryRepository;
import bj.csam.playlist.PlayManager.Repository.PlaylistRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaylistService {
    private final PlaylistRepository repository;
    private final CategoryRepository categoryRepository;
    private final PlaylistMapper Mapper;

    public List<PlaylistDto> findByCategoryId(Long categoryId) {
        return Mapper.toDTOList(repository.findByCategoryId(categoryId));
    }

    public PlaylistDto save(PlaylistDto linkDTO) {
        Playlist link = Mapper.toEntity(linkDTO);

        // Récupérer et assigner la catégorie
        if (linkDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(linkDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
            link.setCategory(category);
        }

        detectPlatform(link);
        Playlist saved = repository.save(link);
        return Mapper.toDTO(saved);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private void detectPlatform(Playlist link) {
        String url = link.getUrl().toLowerCase();
        if (url.contains("youtube.bj.csam") || url.contains("youtu.be")) {
            link.setPlatform("YouTube");
        } else if (url.contains("facebook.bj.csam") || url.contains("fb.bj.csam")) {
            link.setPlatform("Facebook");
        } else if (url.contains("spotify.bj.csam")) {
            link.setPlatform("Spotify");
        } else if (url.contains("soundcloud.bj.csam")) {
            link.setPlatform("SoundCloud");
        } else if (url.contains("deezer.bj.csam")) {
            link.setPlatform("Deezer");
        } else {
            link.setPlatform("Autre");
        }
    }
}