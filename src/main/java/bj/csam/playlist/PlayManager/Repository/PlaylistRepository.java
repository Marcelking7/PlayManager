// ============= PlaylistLinkRepository.java =============
package bj.csam.playlist.PlayManager.Repository;

import bj.csam.playlist.PlayManager.Model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByCategoryId(Long categoryId);
}
