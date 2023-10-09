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
     * @param roleId      the id of the role
     * @param name        the name of the role
     * @param accessLevel the accessLevel of the role
     * @throws NullPointerException if name or accessLevel is null (roleId can be
     *                              because of auto-increment)
     */
    private RoleEntity(int roleId, String name, int accessLevel) {

        requireNonNull(name);

        this.roleId = roleId;
        this.name = name;
        this.accessLevel = accessLevel;
    }

    /**
     * Return a RoleEntity
     *
     * @param roleId      the id of the role
     * @param name        the name of the role
     * @param accessLevel the accessLevel of the role
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
     * Set the roleId
     *
     * @param roleId the roleId to set
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
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
     * Set the name
     *
     * @param name the name to set
     */
    public void setRoleName(String name) {
        this.name = name;
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
     * Set the accessLevel
     *
     * @param accessLevel the accessLevel to set
     */
    public void setRoleAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return "RoleEntity [accessLevel=" + accessLevel + ", name=" + name + ", roleId=" + roleId + "]";
    }

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

    @Override
    public int hashCode() {

        // Hashcode has to be according to equals()
        return roleId + name.hashCode() + accessLevel;

    }

}
