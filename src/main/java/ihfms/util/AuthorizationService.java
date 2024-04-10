package ihfms.util;

import ihfms.model.Role;
import ihfms.model.User;

public class AuthorizationService {
    public boolean authorize(User user, String action) {
        Role role = user.getRole();
        return role != null && role.hasPermission(action);
    }
}