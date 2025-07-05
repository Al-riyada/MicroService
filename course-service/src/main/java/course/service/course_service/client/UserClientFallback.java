package course.service.course_service.client;



public class UserClientFallback implements UserClient {

    @Override
    public UserDTO findUserById(Long id) {
       
        System.err.println("Fallback: User service is not available. Call for user ID: " + id);
        throw new RuntimeException("User service is currently unavailable. Please try again later.");
    }
}
