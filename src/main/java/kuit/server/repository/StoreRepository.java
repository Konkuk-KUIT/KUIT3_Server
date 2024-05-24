package kuit.server.repository;

import kuit.server.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
  boolean existsByBusinessNumber(String businessNumber);
}
