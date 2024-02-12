package pac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pac.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
