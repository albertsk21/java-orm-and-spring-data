package bookshopsystem.services;


import bookshopsystem.entities.Category;
import bookshopsystem.repositories.CategoryRepository;
import bookshopsystem.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void saveCategory(Category category) {
        this.categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Long id) {
        return this.categoryRepository.getById(id);
    }
}
