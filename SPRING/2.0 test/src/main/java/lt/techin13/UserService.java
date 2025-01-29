package lt.techin13;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getUser() {
        return new User("Alice", "AB12345", 12345);
    }
}
