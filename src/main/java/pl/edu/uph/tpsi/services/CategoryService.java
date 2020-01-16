package pl.edu.uph.tpsi.services;

import pl.edu.uph.tpsi.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO create(CategoryDTO categoryDTO);

    List<CategoryDTO> findAll();

    CategoryDTO update(Long id, CategoryDTO categoryDTO);

    void delete(Long id);
}
