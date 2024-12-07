package dasturlash.uz.service;

import dasturlash.uz.dto.post.PostCreateDTO;
import dasturlash.uz.dto.post.PostDTO;
import dasturlash.uz.entity.PostEntity;
import dasturlash.uz.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    protected PostRepository postRepository;

    public PostDTO create(PostCreateDTO dto) {
        PostEntity post = new PostEntity();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        postRepository.save(post); // save
        // post attach save
        PostDTO response = new PostDTO();
        response.setId(post.getId());
        response.setTitle(dto.getTitle());
        response.setContent(dto.getContent());
        return response;
    }

    public PostDTO getById(Integer id) {
        PostEntity post = postRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Post not found");
        });

        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        return postDTO;
    }

    public boolean update(Integer postId, PostDTO postDTO) {
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> {
            throw new RuntimeException("Post not found");
        });
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        postRepository.save(post); // update
        // post attach save
        return true;
    }


}
