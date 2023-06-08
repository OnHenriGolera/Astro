package fr.astro.entity.human;

import static java.util.Objects.requireNonNull;

/**
 * UserEntity
 * 
 * @see PersonEntity
 */
public class UserEntity extends PersonEntity {

    protected RoleEntity roleEntity;
    protected int userId;
    protected String password;

    /**
     * Constructor
     * 
     * @param userEntity
     * @param roleEntity
     * @param password
     * @throws NullPointerException if name, surname, password or
     *                              roleEntity is null (personId and userId can be
     *                              because of auto-increment)
     */
    private UserEntity(int personId, String name, String surname, int userId, String password, RoleEntity roleEntity) {

        super(personId, name, surname);

        requireNonNull(roleEntity);
        requireNonNull(password);

        this.roleEntity = roleEntity;
        this.password = password;
        this.userId = userId;
    }

    /**
     * Return a UserEntity
     * 
     * @param userEntity
     * @param roleEntity
     * @param password
     * @return a UserEntity
     * @throws NullPointerException if name, surname, password or
     *                              roleEntity is null (personId and userId can be
     *                              because of auto-increment)
     */
    public static UserEntity of(int personId, String name, String surname, int userId, String password,
            RoleEntity roleEntity) {
        return new UserEntity(personId, name, surname, userId, password, roleEntity);
    }

    public static UserEntity Of(UserEntity userEntity) {
        return new UserEntity(userEntity.getPersonId(), userEntity.getPersonName(), userEntity.getPersonSurname(),
                userEntity.getUserId(), userEntity.getUserPassword(), userEntity.getUserRoleEntity());
    }

    /**
     * Return the userId
     * 
     * @return userId
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * Return the roleId
     * 
     * @return roleId
     */
    public int getUserRoleId() {
        return roleEntity.getRoleId();
    }

    /**
     * Return the name
     * 
     * @return name
     */
    public String getUserRoleName() {
        return roleEntity.getRoleName();
    }

    /**
     * Return the accessLevel
     * 
     * @return accessLevel
     */
    public int getUserAccessLevel() {
        return roleEntity.getRoleAccessLevel();
    }

    /**
     * Return the password
     * 
     * @return password
     */
    public String getUserPassword() {
        return this.password;
    }

    /**
     * Set the password
     * 
     * @param password
     * @throws NullPointerException if password is null
     */
    public void setUserPassword(String password) {
        requireNonNull(password);
        this.password = password;
    }

    /**
     * Return the RoleEntity
     * 
     * @return roleEntity
     */
    public RoleEntity getUserRoleEntity() {
        return this.roleEntity;
    }

    /**
     * Set the RoleEntity
     * 
     * @param roleEntity
     * @throws NullPointerException if roleEntity is null
     */
    public void setUserRoleEntity(RoleEntity roleEntity) {
        requireNonNull(roleEntity);
        this.roleEntity = roleEntity;
    }

    /**
     * Give a String representation of the UserEntity
     * 
     * @return a String representation of the UserEntity
     */
    @Override
    public String toString() {
        return "UserEntity [userId=" + userId + ", password=" + password + ", roleEntity=" + roleEntity + ", name="
                + name + ", surname=" + surname + ", personId=" + personId + "]";
    }

    /**
     * Equals method
     * 
     * @param obj
     * @return true if the two objects are equals, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof UserEntity))
            return false;
        UserEntity userEntity = (UserEntity) obj;
        return this.userId == userEntity.getUserId()
                && this.password.equals(userEntity.getUserPassword())
                && this.roleEntity.equals(userEntity.getUserRoleEntity())
                && this.name.equals(userEntity.getPersonName())
                && this.surname.equals(userEntity.getPersonSurname())
                && this.personId == userEntity.getPersonId()
                && this.hashCode() == userEntity.hashCode();
    }

}
