package fr.astro.entity.human;

import static java.util.Objects.requireNonNull;

import fr.astro.entity.field.Gender;

/**
 * UserEntity
 * 
 * @see PersonEntity
 * @see RoleEntity
 */
public class UserEntity {

    private RoleEntity roleEntity;
    private int userId;
    private String password;
    private PersonEntity personEntity;

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
    private UserEntity(int personId, String name, String surname, String gender, String birthDate, int userId,
            String password, RoleEntity roleEntity) {
        requireNonNull(roleEntity);
        requireNonNull(password);

        this.personEntity = PersonEntity.of(personId, name, surname, gender, birthDate);

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
    public static UserEntity of(int personId, String name, String surname, String gender, String birthDate, int userId,
            String password, RoleEntity roleEntity) {
        return new UserEntity(personId, name, surname, gender, birthDate, userId, password, roleEntity);
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
    public static UserEntity of(int personId, String name, String surname, Gender gender, String birthDate, int userId,
            String password, RoleEntity roleEntity) {
        return new UserEntity(personId, name, surname, gender.getName(), birthDate, userId, password, roleEntity);
    }

    public static UserEntity of(UserEntity userEntity) {
        return UserEntity.of(
                userEntity.getPersonId(),
                userEntity.getPersonName(),
                userEntity.getPersonSurname(),
                userEntity.getPersonGender(),
                userEntity.getPersonBirthDate(),
                userEntity.getUserId(),
                userEntity.getUserPassword(),
                userEntity.getUserRoleEntity());
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
     * Return the name
     * 
     * @return name
     */
    public String getPersonName() {
        return personEntity.getPersonName();
    }

    /**
     * Return the surname
     * 
     * @return surname
     */
    public String getPersonSurname() {
        return personEntity.getPersonSurname();
    }

    /**
     * Return the personId
     * 
     * @return personId
     */
    public int getPersonId() {
        return personEntity.getPersonId();
    }

    /**
     * Get the person gender
     * 
     * @param gender
     */
    public Gender getPersonGender() {
        return personEntity.getPersonGender();
    }

    /**
     * Get the person birth date
     * 
     * @param birthDate
     */
    public String getPersonBirthDate() {
        return personEntity.getPersonBirthDate();
    }

    /**
     * Set the name
     * 
     * @param name
     * @throws NullPointerException if name is null
     */
    public void setPersonName(String name) {
        requireNonNull(name);
        personEntity.setPersonName(name);
    }

    /**
     * Set the surname
     * 
     * @param surname
     * @throws NullPointerException if surname is null
     */
    public void setPersonSurname(String surname) {
        requireNonNull(surname);
        personEntity.setPersonSurname(surname);
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
     * Return the PersonEntity
     * 
     * @return personEntity
     */
    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    /**
     * Give a String representation of the UserEntity
     * 
     * @return a String representation of the UserEntity
     */
    @Override
    public String toString() {
        return "UserEntity [userId=" +
                userId +
                ", password=" +
                password +
                ", roleEntity=" +
                roleEntity +
                ", personEntity=" +
                personEntity +
                "]";
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

        // Check each attribute
        return userEntity.getUserId() == userId
                && userEntity.getUserPassword().equals(password)
                && userEntity.getUserRoleEntity().equals(roleEntity)
                && userEntity.getPersonEntity().equals(personEntity)
                && userEntity.getPersonName().equals(personEntity.getPersonName())
                && userEntity.getPersonSurname().equals(personEntity.getPersonSurname())
                && userEntity.getPersonGender().equals(personEntity.getPersonGender())
                && userEntity.getPersonBirthDate().equals(personEntity.getPersonBirthDate());

    }

    /**
     * HashCode method
     * 
     * @return hashCode
     */
    @Override
    public int hashCode() {

        // HashCode has to be according to the equals method
        return 31 * userId
                + password.hashCode()
                + roleEntity.hashCode()
                + personEntity.hashCode()
                + personEntity.getPersonName().hashCode()
                + personEntity.getPersonSurname().hashCode()
                + personEntity.getPersonGender().hashCode()
                + personEntity.getPersonBirthDate().hashCode();

    }

}
