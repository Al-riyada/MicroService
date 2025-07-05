package course.service.course_service.controllers;

import course.service.course_service.entities.Course;
import course.service.course_service.entities.CourseStatus;
import course.service.course_service.repositories.CourseRepository;
import course.service.course_service.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;


     @Autowired
    private CourseService courseService;



    // Endpoint for an instructor to create a new course
    // POST http://localhost:PORT/api/courses
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course courseRequest) {
        
       
        Course createdCourse = courseService.createCourse(courseRequest);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }
    
    // Endpoint for a student to see all approved courses
    // GET http://localhost:PORT/api/courses
    @GetMapping
    public List<Course> getApprovedCourses() {
        return courseRepository.findByStatus(CourseStatus.APPROVED);
    }
    
    // Endpoint for an admin to approve a course
    // PUT http://localhost:PORT/api/courses/{id}/approve
    @PutMapping("/{id}/approve")
    public ResponseEntity<Course> approveCourse(@PathVariable Long id) {
        return courseRepository.findById(id).map(course -> {
            course.setStatus(CourseStatus.APPROVED);
            Course updatedCourse = courseRepository.save(course);
            return ResponseEntity.ok(updatedCourse);
        }).orElse(ResponseEntity.notFound().build());
    }
}
