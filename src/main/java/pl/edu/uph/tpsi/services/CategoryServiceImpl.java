package pl.edu.uph.tpsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uph.tpsi.dto.CategoryDTO;
import pl.edu.uph.tpsi.mappers.CategoryMapper;
import pl.edu.uph.tpsi.models.Category;
import pl.edu.uph.tpsi.repositories.CategoryRepository;

import javax.persistence.Access;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("accessoryService")
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        Category category = categoryMapper.DTOtoCategory(categoryDTO);
        category.setDeleted(false);
        categoryRepository.save(category);
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        return categoryRepository.findById(id)
                .map(e -> {
                    e.setName(categoryDTO.getName());
                    categoryRepository.save(e);
                    return categoryMapper.categoryToDTO(e);
                }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.findById(id)
                .ifPresent(e -> {
                    e.setDeleted(!e.getDeleted());
                    categoryRepository.save(e);
                });
    }
}
