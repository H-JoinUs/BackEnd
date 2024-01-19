package H.joinUs.repository;

import H.joinUs.domain.Following;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowingRepository extends JpaRepository<Following, Long> {
}
