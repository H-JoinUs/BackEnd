package H.joinUs.repository;

import H.joinUs.domain.Comment;
import H.joinUs.domain.Post;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndPost(Long id, Post post);
    List<Comment> findCommentsByParentAndPost(@Nullable Comment parent, Post post);
    Optional<Comment> findByIdAndParentAndPost(Long id, Comment parent, Post post);

    List<Comment> findAllByParent(Comment comment);
}
