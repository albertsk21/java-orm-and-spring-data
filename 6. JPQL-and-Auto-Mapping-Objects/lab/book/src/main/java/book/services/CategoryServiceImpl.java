package book.services;

import book.entities.Category;
import book.repositories.CategoryRepository;
import book.services.interfaces.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void saveCategory(Category category) {
        this.categoryRepository.save(category);
    }

    @Override
    public int categoriesSize() {
      return (int) this.categoryRepository.count();
    }

    @Override
    public Category findById(Long id){
      return this.categoryRepository.findCategoryById(id);
    }

}
