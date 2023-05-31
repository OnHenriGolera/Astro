package src.fr.astro.entity;

import static java.util.Objects.requireNonNull;

/**
 * RoleEntity
 */
public class RoleEntity {
    
    private int roleId;
    private String name;
    private int accessLevel;

    /**
     * Constructor
     * 
     * @param roleId
     * @param name
     * @param accessLevel
     * @throws NullPointerException if roleId, name or accessLevel is null
     */
    private RoleEntity(int roleId, String name, int accessLevel) {

        requireNonNull(roleId);
        requireNonNull(name);
        requireNonNull(accessLevel);

        this.roleId = roleId;
        this.name = name;
        this.accessLevel = accessLevel;
    }

    /**
     * Return a RoleEntity
     * 
     * @param roleId
     * @param name
     * @param accessLevel
     * @return a RoleEntity
     * @throws NullPointerException if roleId, name or accessLevel is null
     */
    public static RoleEntity of(int roleId, String name, int accessLevel) {
        return new RoleEntity(roleId, name, accessLevel);
    }

    /**
     * Return the roleId
     * 
     * @return roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * Return the name
     * 
     * @return name
     */
    public String getRoleName() {
        return name;
    }

    /**
     * Return the accessLevel
     * 
     * @return accessLevel
     */
    public int getRoleAccessLevel() {
        return accessLevel;
    }

    /**
     * Return a String representation of the object
     * 
     * @return a String representation of the object
     */
    @Override
    public String toString() {
        return "RoleEntity [accessLevel=" + accessLevel + ", name=" + name + ", roleId=" + roleId + "]";
    }

}
