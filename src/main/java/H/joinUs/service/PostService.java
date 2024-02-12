package H.joinUs.service;

import H.joinUs.domain.Post;
import H.joinUs.dto.RequestDto.PostRequestDto;
import H.joinUs.dto.ResponseDto.PostResponseDto;
import H.joinUs.dto.ResponseDto.UserResponseDto;
import H.joinUs.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostResponseDto.CreatePost create(PostRequestDto.CreatePost request){

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        postRepository.save(post);

        return PostResponseDto.CreatePost.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    @Transactional
    public PostResponseDto.UpdatePost update(Long postId, PostRequestDto.UpdatePost request) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        post.update(request.getTitle(), request.getContent());

        return PostResponseDto.UpdatePost.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    @Transactional
    public void delete (Long postId) {

        postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        postRepository.deleteById(postId);
    }

    public PostResponseDto.GetPost getDetail(Long postId){

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));


        return PostResponseDto.GetPost.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                //.user(UserResponseDto.BriefUserInfo.builder().userId().build())
                .build();
    }

    public List<PostResponseDto.GetPost> get() {
        List<Post> postList = postRepository.findAll();
        List<PostResponseDto.GetPost> result = new ArrayList<>();
        for(Post post: postList){
            PostResponseDto.GetPost getPost = PostResponseDto.GetPost.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .createdAt(post.getCreatedAt())
                    .build();
            result.add(getPost);
        }

        return result;
    }


}
