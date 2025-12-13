package bj.csam.playlist.PlayManager.Repository;

import bj.csam.playlist.PlayManager.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}