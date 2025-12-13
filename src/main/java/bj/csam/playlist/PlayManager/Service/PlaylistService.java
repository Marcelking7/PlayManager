

// ============= PlaylistLinkService.java =============
package bj.csam.playlist.PlayManager.Service;

import bj.csam.playlist.PlayManager.Dto.PlaylistDto;
import bj.csam.playlist.PlayManager.Mapper.PlaylistMapper;
import bj.csam.playlist.PlayManager.Model.Category;
import bj.csam.playlist.PlayManager.Model.Playlist;
import bj.csam.playlist.PlayManager.Repository.CategoryRepository;
import bj.csam.playlist.PlayManager.Repository.PlaylistRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PlaylistService {
    private final PlaylistRepository repository;
    private final CategoryRepository categoryRepository;
    private final PlaylistMapper Mapper;

    public List<PlaylistDto> findByCategoryId(Long categoryId) {
        log.info("Récupération des liens pour la catégorie ID: {}", categoryId);
        return Mapper.toDTOList(repository.findByCategoryId(categoryId));
    }

    public PlaylistDto save(PlaylistDto linkDTO) {
        log.info("Sauvegarde du lien: {}", linkDTO.getTitle());
        Playlist link = Mapper.toEntity(linkDTO);

        // Récupérer et assigner la catégorie
        if (linkDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(linkDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
            link.setCategory(category);
        }

        detectPlatform(link);
        Playlist saved = repository.save(link);
        log.info("Lien sauvegardé avec ID: {}", saved.getId());
        return Mapper.toDTO(saved);
    }

    public void deleteById(Long id) {
        log.info("Suppression du lien avec ID: {}", id);
        repository.deleteById(id);
    }

    private void detectPlatform(Playlist link) {
        String originalUrl = link.getUrl();
        if (originalUrl == null) {
            link.setPlatform("Autre");
            return;
        }
        String url = originalUrl.toLowerCase();
        if (url.contains("youtube.com") || url.contains("youtu.be")) {
            link.setPlatform("YouTube");
        } else if (url.contains("facebook.com") || url.contains("fb.com")) {
            link.setPlatform("Facebook");
        } else if (url.contains("spotify.com")) {
            link.setPlatform("Spotify");
        } else if (url.contains("soundcloud.com")) {
            link.setPlatform("SoundCloud");
        } else if (url.contains("deezer.com")) {
            link.setPlatform("Deezer");
        } else {
            link.setPlatform("Autre");
        }
    }
}