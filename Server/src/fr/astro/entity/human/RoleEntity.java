package fr.astro.entity.human;

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
     * @throws NullPointerException if name or accessLevel is null (roleId can be
     *                              because of auto-increment)
     */
    private RoleEntity(int roleId, String name, int accessLevel) {

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
     * @throws NullPointerException if name or accessLevel is null (roleId can be
     *                              because of auto-increment)
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
     * Set the roleId
     * 
     * @param roleId
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * Set the name
     * 
     * @param name
     */
    public void setRoleName(String name) {
        this.name = name;
    }

    /**
     * Set the accessLevel
     * 
     * @param accessLevel
     */
    public void setRoleAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
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

    /**
     * Indicates whether some other object is "equal to" this one.
     * 
     * @param obj the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == this)
            return true;

        if (!(obj instanceof RoleEntity))
            return false;

        RoleEntity role = (RoleEntity) obj;

        // Check each field
        return role.getRoleId() == roleId
                && role.getRoleName().equals(name)
                && role.getRoleAccessLevel() == accessLevel;

    }

    /**
     * Returns a hash code value for the object.
     * 
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {

        // Hashcode has to be according to equals()
        return roleId + name.hashCode() + accessLevel;

    }

}
