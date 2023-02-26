package com.ameya.schedulemicroservice.repository;

import java.util.List;

import com.ameya.schedulemicroservice.entity.Schedule;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {
	
	@Query(value = "SELECT * FROM schedules WHERE movie_id=1 AND theater_id=2 AND showtime_id=3",nativeQuery = true)
	Schedule findExistingSchedule(int matchId, int theaterId, int showtimeId);
	
	List<Schedule> findByMovie(int movieId);
	
	List<Schedule> findByTheater(int theaterId);
	
	List<Schedule> findByShowtime(int showtimeId);

}
