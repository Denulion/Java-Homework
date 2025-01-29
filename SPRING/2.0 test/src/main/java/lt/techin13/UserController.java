package lt.techin13;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUser() {
        User user = userService.getUser();
        return "Name: " + user.getName() + "<br>" +
                "Personal Code: " + user.getPersonalCode() + "<br>" +
                "Balance (EUR): " + user.getBalance();
    }
}
