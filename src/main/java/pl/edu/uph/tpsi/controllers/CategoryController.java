package pl.edu.uph.tpsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.uph.tpsi.dto.CategoryDTO;
import pl.edu.uph.tpsi.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping ("/api/category")
@CrossOrigin (origins = "http://localhost:4200")
public class CategoryController
{
        private final CategoryService categoryService;

        @Autowired
        public CategoryController ( CategoryService categoryService )
        {
                this.categoryService = categoryService;
        }

        @GetMapping
        public List<CategoryDTO> findAll ()
        {
                return categoryService.findAll();
        }

        @PostMapping
        public CategoryDTO create ( @RequestBody CategoryDTO categoryDTO )
        {
                return categoryService.create( categoryDTO );
        }
}
