package dasturlash.uz.controller;

import dasturlash.uz.dto.post.PostCreateDTO;
import dasturlash.uz.dto.post.PostDTO;
import dasturlash.uz.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@Tag(name = "Post APIs", description = "API list for managing Post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("")
    @Operation(summary = "Create post", description = "Api used for creating new post")
    public PostDTO create(@RequestBody PostCreateDTO postDTO) {
        PostDTO result = postService.create(postDTO);
        return result;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get post by id", description = "API used for getting post by id")
    public PostDTO byId(@PathVariable("id") Integer id) {
        PostDTO result = postService.getById(id);
        return result;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update post", description = "Api used for updating post by id")
    public Boolean update(@PathVariable("id") Integer id,
                          @RequestBody PostDTO postDTO) {
        Boolean result = postService.update(id, postDTO);
        return result;
    }
}
