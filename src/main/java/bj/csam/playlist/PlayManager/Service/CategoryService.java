package bj.csam.playlist.PlayManager.Service;

import bj.csam.playlist.PlayManager.Dto.CategoryDto;
import bj.csam.playlist.PlayManager.Mapper.CategoryMapper;
import bj.csam.playlist.PlayManager.Model.Category;
import bj.csam.playlist.PlayManager.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public List<CategoryDto> findAll() {
        log.info("Récupération de toutes les catégories");
        return mapper.toDTOList(repository.findAll());
    }

    public CategoryDto findById(Long id) {
        log.info("Récupération de la catégorie avec ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public CategoryDto save(CategoryDto categoryDto) {
        log.info("Sauvegarde de la catégorie: {}", categoryDto.getName());
        Category category = mapper.toEntity(categoryDto);
        Category saved = repository.save(category);
        log.info("Catégorie sauvegardée avec ID: {}", saved.getId());
        return mapper.toDTO(saved);
    }

    @Transactional
    public void deleteById(Long id) {
        log.info("Suppression de la catégorie avec ID: {}", id);
        repository.deleteById(id);
    }
}