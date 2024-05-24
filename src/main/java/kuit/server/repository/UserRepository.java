package kuit.server.repository;

import kuit.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByEmail(String email);
  boolean existsByNickname(String nickname);

  @Query("select u from user u where u.nickname like :nickname and u.email like :email and u.status=:status")
  List<User> getUsers(@Param("nickname") String nickname, @Param("email") String email, @Param("status") String status);
}
