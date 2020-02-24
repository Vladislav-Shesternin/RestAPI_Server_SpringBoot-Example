package myapp.repositories;

import java.util.List;

import myapp.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findByTitleContainingOrContentContaining(String var1, String var2);
}
