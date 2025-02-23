package security.lesson_1.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import security.lesson_1.dto.User;
import security.lesson_1.model.UserProfile;
import security.lesson_1.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserProfile getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new UserProfile(user.getUsername());
    }
}
