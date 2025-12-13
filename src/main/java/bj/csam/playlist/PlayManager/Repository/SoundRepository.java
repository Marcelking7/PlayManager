package bj.csam.playlist.PlayManager.Repository;

import bj.csam.playlist.PlayManager.Model.Sound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoundRepository extends JpaRepository<Sound, Long> {
    List<Sound> findByEventType(String eventType);
}