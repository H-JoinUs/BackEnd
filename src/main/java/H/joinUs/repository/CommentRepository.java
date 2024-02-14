package H.joinUs.repository;

import H.joinUs.domain.Comment;
import H.joinUs.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndPost(Long id, Post post);
}
