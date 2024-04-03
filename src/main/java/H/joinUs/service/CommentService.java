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
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

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
        List<Comment> commentList = commentRepository.findCommentsByParentAndPost(null,post);

        List<CommentResponseDto.GetComment> result = new ArrayList<>();

        for(Comment comment: commentList){
            List<CommentResponseDto.GetRecomment> resultRecomment = new ArrayList<>();
            List<Comment> comments = commentRepository.findAllByParent(comment);
            for(Comment recomment : comments){
                CommentResponseDto.GetRecomment getRecomment = CommentResponseDto.GetRecomment.builder()
                        .id(recomment.getId())
                        .content(recomment.getContent())
                        .createdAt(recomment.getCreatedAt())
                        .build();
                resultRecomment.add(getRecomment);
            }
            CommentResponseDto.GetComment getComment = CommentResponseDto.GetComment.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .createdAt(comment.getCreatedAt())
                    .recomment(resultRecomment)
                    .build();
            result.add(getComment);
        }
        return result;
    }

    @Transactional
    public CommentResponseDto.CreateRecomment createRecomment(Long postId, Long commentId, CommentRequestDto.CreateRecomment request){

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        Comment comment = commentRepository.findByIdAndPost(commentId,post)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

        Comment recomment = Comment.builder()
                .post(post)
                .content(request.getContent())
                .parent(comment)
                .build();
        commentRepository.save(recomment);

        return CommentResponseDto.CreateRecomment.builder()
                .id(recomment.getId())
                .content(recomment.getContent())
                .build();
    }

    @Transactional
    public CommentResponseDto.UpdateRecomment updateRecomment(Long postId, Long commentId, Long recommentId, CommentRequestDto.UpdateRecomment request){

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));



        Comment recomment = commentRepository.findByIdAndParentAndPost(recommentId,comment,post)
                .orElseThrow(() -> new RuntimeException("대댓글을 찾을 수 없습니다."));


        recomment.update(request.getContent());

        return CommentResponseDto.UpdateRecomment.builder()
                .id(recomment.getId()).content(recomment.getContent())
                .build();
    }

    @Transactional
    public void deleteRecomment(Long postId, Long commentId, Long recommentId){

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        Comment comment = commentRepository.findByIdAndPost(commentId,post)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

        Comment recomment = commentRepository.findByIdAndParentAndPost(recommentId, comment, post)
                .orElseThrow(() -> new RuntimeException("대댓글을 찾을 수 없습니다."));

        commentRepository.delete(recomment);
    }
}
