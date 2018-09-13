package com.wallethub.domain.model.repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.wallethub.domain.model.Access;

@Repository
public interface AccessRepository extends JpaRepository<Access, Long> {

	@Query(value = "Select  l.ip as IP from logs_table l" + " where l.date BETWEEN :startDate and :endDate"
			+ " GROUP BY ip " + "HAVING COUNT(l.date) > :threshold", nativeQuery = true)
	List<BigInteger> findIpByDateAndThreShold(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate,
			@Param("threshold") long threshold);

}
