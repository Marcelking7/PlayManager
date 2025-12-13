package bj.csam.playlist.PlayManager.Mapper;

// ============= CategoryMapper.java =============


import bj.csam.playlist.PlayManager.Dto.CategoryDto;
import bj.csam.playlist.PlayManager.Model.Category;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryDto toDTO(Category category) {
        if (category == null) return null;

        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setLinksCount(category.getLinks() != null ? category.getLinks().size() : 0);
        return dto;
    }

    public Category toEntity(CategoryDto dto) {
        if (dto == null) return null;

        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }

    public List<CategoryDto> toDTOList(List<Category> categories) {
        return categories.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
