package course.service.course_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import course.service.course_service.client.UserClient;
import course.service.course_service.client.UserDTO;
import course.service.course_service.entities.Course;
import course.service.course_service.repositories.CourseRepository;
//import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserClient userClient; 

    private static final Logger log = LoggerFactory.getLogger(CourseService.class);

    public Course createCourse(Course course) {
        Long instructorId = course.getInstructorId();
        UserDTO instructor = userClient.findUserById(instructorId);

        if (instructor == null) {
                log.error("Instructor verification failed: UserDTO is null or has no ID.");
                throw new RuntimeException("Error with connect with user-service ");
        }


        if (instructor.getId() == null) {
                log.error("Instructor verification failed: UserDTO is null or has no ID.");
                throw new RuntimeException("Invalid instructor ID: " + instructorId + ". User not found.");
        }

        if (!"INSTRUCTOR".equals(instructor.getRole())) {
            log.error("Verification failed: User is not an INSTRUCTOR.");
            throw new RuntimeException("User with ID " + instructorId + " is not an instructor.");
        }

        log.info("Instructor verified successfully: " + instructor.getName());

        return courseRepository.save(course);

       
    }
}