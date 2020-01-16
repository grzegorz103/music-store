package pl.edu.uph.tpsi.mappers;

import org.mapstruct.Mapper;
import pl.edu.uph.tpsi.dto.CategoryDTO;
import pl.edu.uph.tpsi.models.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO categoryToDTO(Category category);

    Category DTOtoCategory(CategoryDTO categoryDTO);
}