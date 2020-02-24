package myapp.controllers;

import java.util.List;
import java.util.Map;

import myapp.models.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import myapp.repositories.BlogRepository;

@RestController
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    public BlogController() {
    }

    @GetMapping({"/blog"})
    public List<Blog> index() {
        return this.blogRepository.findAll();
    }

    @GetMapping({"/blog/{id}"})
    public Blog show(@PathVariable String id) {
        int blogId = Integer.parseInt(id);
        return (Blog) this.blogRepository.findOne(blogId);
    }

    @PostMapping({"/blog/search"})
    public List<Blog> search(@RequestBody Map<String, String> body) {
        String searchTerm = (String) body.get("text");
        return this.blogRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }

    @PostMapping({"/blog"})
    public Blog create(@RequestBody Map<String, String> body) {
        String title = (String) body.get("title");
        String content = (String) body.get("content");
        return (Blog) this.blogRepository.save(new Blog(title, content));
    }

    @PutMapping({"/blog/{id}"})
    public Blog update(@PathVariable String id, @RequestBody Map<String, String> body) {
        int blogId = Integer.parseInt(id);
        Blog blog = (Blog) this.blogRepository.findOne(blogId);
        blog.setTitle((String) body.get("title"));
        blog.setContent((String) body.get("content"));
        return (Blog) this.blogRepository.save(blog);
    }

    @DeleteMapping({"blog/{id}"})
    public boolean delete(@PathVariable String id) {
        int blogId = Integer.parseInt(id);
        this.blogRepository.delete(blogId);
        return true;
    }
}
