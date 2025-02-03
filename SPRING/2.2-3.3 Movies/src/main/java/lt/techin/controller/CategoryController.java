package lt.techin.controller;

import lt.techin.model.Category;
import lt.techin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired //not required if there is only one constructor, but it is recommended have it
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        if (category.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name is empty");
        }

        if (categoryService.existsCategoryByName(category.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A category with this name already exists!");
        }

        Category savedCategory = categoryService.saveCategory(category);

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(savedCategory.getId())
                                .toUri())
                .body(category);
    }
}
