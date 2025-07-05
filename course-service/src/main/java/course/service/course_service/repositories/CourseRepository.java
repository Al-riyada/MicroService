package course.service.course_service.repositories;


import course.service.course_service.entities.Course;
import course.service.course_service.entities.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Custom method to find all courses with a specific status
    List<Course> findByStatus(CourseStatus status);
}