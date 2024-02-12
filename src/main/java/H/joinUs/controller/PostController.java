package H.joinUs.controller;

import H.joinUs.dto.RequestDto.PostRequestDto;
import H.joinUs.dto.ResponseDto.PostResponseDto;
import H.joinUs.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping(value = "/post")
    public ResponseEntity<PostResponseDto.CreatePost> createPost(@RequestBody PostRequestDto.CreatePost request) {
        return ResponseEntity.ok(postService.create(request));
    }

    @PutMapping(value = "/post/{postId}")
    public ResponseEntity<PostResponseDto.UpdatePost> updatePost(@PathVariable(name = "postId") Long postId, @RequestBody PostRequestDto.UpdatePost request) {
        return ResponseEntity.ok(postService.update(postId, request));
    }

    // 상세 조회
    @GetMapping(value = "/post/{postId}")
    public ResponseEntity<PostResponseDto.GetPost> getDetail(@PathVariable(name = "postId") Long postId){
        return ResponseEntity.ok(postService.getDetail(postId));
    }

    // 리스트 조회
    @GetMapping (value = "/post/list")
    public ResponseEntity<List<PostResponseDto.GetPost>> getList(){
        return ResponseEntity.ok(postService.get());
    }

    @DeleteMapping(value = "/post/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable(name = "postId") Long postId){
        postService.delete(postId);
        return ResponseEntity.ok().build();
    }
}