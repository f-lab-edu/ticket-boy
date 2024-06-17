package study.flab.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import study.flab.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  // 이메일로 회원정보 조회
  // optional = null방지
  Optional<UserEntity> findByUserEmail(String userEmail);

//  Optional<UserEntity> findByUserNickname(String userNickname);
}
