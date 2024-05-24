package kuit.server.repository;

import kuit.server.domain.Menu;
import kuit.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
  boolean existsByName(String menuName);

  Optional<Menu> findByStoreIdAndName(long storeId, String menuName);

  List<Menu> getMenus();
}
