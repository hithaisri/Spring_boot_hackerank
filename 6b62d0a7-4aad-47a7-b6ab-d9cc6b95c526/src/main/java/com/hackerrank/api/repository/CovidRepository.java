package com.hackerrank.api.repository;

import com.hackerrank.api.model.Covid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CovidRepository extends JpaRepository<Covid, Long> {
	
	Optional<Covid> findById(Long id);

	@Query(value="select TOP 5 from covid order by ?1",nativeQuery=true)
	List<Covid> getTop5(String by);
	
	@Query(value = "SELECT SUM(?1) FROM covid", nativeQuery = true)
	Integer getTotal(String by);
	

}
