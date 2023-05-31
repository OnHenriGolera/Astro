package src.fr.astro.entity;

import static java.util.Objects.requireNonNull;

/**
 * UserEntity
 * 
 * @see PersonEntity
 */
public class UserEntity {

    private PersonEntity userEntity;
    private RoleEntity roleEntity;
    private int userId;
    private String password;

    /**
     * Constructor
     * 
     * @param userEntity
     * @param roleEntity
     * @param password
     * @throws NullPointerException if userId, name, surname, roleId or password is
     *                              null
     */
    private UserEntity(int userId, String password, PersonEntity userEntity, RoleEntity roleEntity) {
        requireNonNull(userEntity);
        requireNonNull(roleEntity);
        requireNonNull(password);
        requireNonNull(userId);

        this.userEntity = userEntity;
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
     * @throws NullPointerException if userId, name, surname, roleId or password is
     *                              null
     */
    public static UserEntity of(int userId, String password, PersonEntity userEntity, RoleEntity roleEntity) {
        return new UserEntity(userId, password, userEntity, roleEntity);
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
     * Return the name
     * 
     * @return name
     */
    public String getUserName() {
        return userEntity.getPersonName();
    }

    /**
     * Return the surname
     * 
     * @return surname
     */
    public String getUserSurname() {
        return userEntity.getPersonSurname();
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
     * Set the name
     * 
     * @param name
     * @throws NullPointerException if name is null
     */
    public void setUserName(String name) {
        userEntity.setPersonName(name);
    }

    /**
     * Set the surname
     * 
     * @param surname
     * @throws NullPointerException if surname is null
     */
    public void setUserSurname(String surname) {
        userEntity.setPersonSurname(surname);
    }

    /**
     * Give a String representation of the UserEntity
     * 
     * @return a String representation of the UserEntity
     */
    @Override
    public String toString() {
        return "UserEntity [userEntity=" + userEntity + ", roleEntity=" + roleEntity + ", password=" + password + "]";
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
        return this.userId == userEntity.getUserId();
    }

}
