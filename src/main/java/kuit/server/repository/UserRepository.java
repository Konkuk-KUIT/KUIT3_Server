package kuit.server.repository;

import kuit.server.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByEmail(String email);
  boolean existsByNickname(String nickname);

  Optional<User> findByEmail(String email);

  Optional<User> findByUserId(long userId);

  @Query("select u from user u where u.nickname like :nickname and u.email like :email and u.status=:status")
  List<User> getUsers(@Param("nickname") String nickname, @Param("email") String email, @Param("status") String status, Pageable pageable);
}
