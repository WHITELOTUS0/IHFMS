package ihfms.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Role {
    private String name;
    private Set<String> permissions;

    private static final Map<String, Role> roles = new HashMap<>();

    static {
        // Define roles and their permissions
        Role adminRole = new Role("admin");
        adminRole.addPermission("create_invoice");
        adminRole.addPermission("view_dashboard");
        // Add more permissions as needed

        Role userRole = new Role("user");
        userRole.addPermission("view_invoice");
        // Add more permissions as needed

        roles.put(adminRole.getName(), adminRole);
        roles.put(userRole.getName(), userRole);
    }

    public Role(String name) {
        this.name = name;
        this.permissions = new HashSet<>();
    }

    public void addPermission(String permission) {
        permissions.add(permission);
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }

    public String getName() {
        return name;
    }

    public static Role getRoleByName(String roleName) {
        return roles.get(roleName);
    }
}