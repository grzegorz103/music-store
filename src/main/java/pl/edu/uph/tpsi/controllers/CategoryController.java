package pl.edu.uph.tpsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.uph.tpsi.dto.CategoryDTO;
import pl.edu.uph.tpsi.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = {"http://localhost:4200", "https://music-store-2620.firebaseapp.com/"})
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<CategoryDTO> findAll() {
        return categoryService.findAll();
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public CategoryDTO create(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.create(categoryDTO);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public CategoryDTO update(@PathVariable Long id,
                              @RequestBody CategoryDTO categoryDTO) {
        return categoryService.update(id, categoryDTO);
    }

}
