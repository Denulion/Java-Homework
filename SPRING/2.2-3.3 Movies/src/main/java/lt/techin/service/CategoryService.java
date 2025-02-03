package lt.techin.service;

import lt.techin.model.Category;
import lt.techin.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public boolean existsCategoryByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
