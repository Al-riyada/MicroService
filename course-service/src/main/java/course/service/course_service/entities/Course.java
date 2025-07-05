package course.service.course_service.entities;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    // This will store the ID of the instructor from the User Service.
    // It's the link between the two services.
    private Long instructorId; 

    private double price;

    @Enumerated(EnumType.STRING)
    private CourseStatus status;
}