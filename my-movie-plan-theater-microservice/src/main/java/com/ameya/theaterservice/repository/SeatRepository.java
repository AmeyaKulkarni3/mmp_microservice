package com.ameya.theaterservice.repository;

import java.util.List;

import com.ameya.theaterservice.entity.Seat;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Integer> {

	@Query(value="SELECT * FROM seats WHERE schedule_id=1",nativeQuery = true)
	List<Seat> findBySchedule(int id);

}
