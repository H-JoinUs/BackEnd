package H.joinUs.service;

import H.joinUs.domain.Comment;
import H.joinUs.domain.Post;
import H.joinUs.dto.RequestDto.CommentRequestDto;
import H.joinUs.dto.RequestDto.PostRequestDto;
import H.joinUs.dto.ResponseDto.CommentResponseDto;
import H.joinUs.dto.ResponseDto.PostResponseDto;
import H.joinUs.repository.CommentRepository;
import H.joinUs.repository.PostRepository;
import H.joinUs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentResponseDto.CreateComment create(Long postId, CommentRequestDto.CreateComment request){

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        Comment comment = Comment.builder()
                .post(post)
                .content(request.getContent())
                .build();
        commentRepository.save(comment);

        return CommentResponseDto.CreateComment.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .build();
    }

    @Transactional
    public CommentResponseDto.UpdateComment update(Long postId, Long commentId, CommentRequestDto.UpdateComment request){

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        Comment comment = commentRepository.findByIdAndPost(commentId,post)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

        comment.update(request.getContent());

        return CommentResponseDto.UpdateComment.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .build();
    }

    @Transactional
    public void delete(Long postId, Long commentId){

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        Comment comment = commentRepository.findByIdAndPost(commentId,post)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

        commentRepository.delete(comment);
    }

    public List<CommentResponseDto.GetComment> get(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        List<Comment> commentList = commentRepository.findCommentsByPost(post);

        List<CommentResponseDto.GetComment> result = new ArrayList<>();

        for(Comment comment: commentList){
            CommentResponseDto.GetComment getComment = CommentResponseDto.GetComment.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .createdAt(comment.getCreatedAt())
                    .build();
            result.add(getComment);
        }

        return result;
    }

    @Transactional
    public CommentResponseDto.CreateRecomment createRecomment(Long postId, Long commentId, CommentRequestDto.CreateRecomment request){

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

        Comment recomment = Comment.builder()
                .post(post)
                .content(request.getContent())
                .comment(comment)
                .build();
        commentRepository.save(recomment);

        return CommentResponseDto.CreateRecomment.builder()
                .id(recomment.getId())
                .content(recomment.getContent())
                .build();
    }
}
