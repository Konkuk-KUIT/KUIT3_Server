package kuit.server.repository;

import kuit.server.domain.Menu;
import kuit.server.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
  boolean existsByName(String menuName);

  Slice<Menu> findAllByStoreId(long storeId, Pageable pageable);

  Optional<Menu> findByStoreIdAndMenuId(long storeId, long menuId);
}
