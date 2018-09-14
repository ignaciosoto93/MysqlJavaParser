package com.wallethub.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wallethub.domain.model.BlockedIP;
@Repository
public interface BlockedIPRepository extends JpaRepository<BlockedIP, Long> {
}
