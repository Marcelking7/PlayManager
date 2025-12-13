
// ============= CategoryRepository.java =============
package bj.csam.playlist.PlayManager.Repository;

import bj.csam.playlist.PlayManager.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
