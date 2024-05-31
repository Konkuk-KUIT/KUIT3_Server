package kuit.server.repository;

import kuit.server.domain.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
  boolean existsByBusinessNumber(String businessNumber);

  Slice<Store> findAllByStatusIs(Pageable pageable, String status);
}
