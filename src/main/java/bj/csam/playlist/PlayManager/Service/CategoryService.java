package bj.csam.playlist.PlayManager.Service;

import bj.csam.playlist.PlayManager.Dto.CategoryDto;
import bj.csam.playlist.PlayManager.Mapper.CategoryMapper;
import bj.csam.playlist.PlayManager.Model.Category;
import bj.csam.playlist.PlayManager.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public List<CategoryDto> findAll() {
        return mapper.toDTOList(repository.findAll());
    }

    public CategoryDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = mapper.toEntity(categoryDto);
        Category saved = repository.save(category);
        return mapper.toDTO(saved);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}