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

    private final int userId;
    private final PersonEntity personEntity;
    private RoleEntity roleEntity;
    private String password;

    /**
     * Constructor
     *
     * @param personId   the id of the person
     * @param name       the name of the person
     * @param surname    the surname of the person
     * @param gender     the gender of the person
     * @param birthDate  the birthDate of the person
     * @param userId     the id of the user
     * @param password   the password of the user
     * @param roleEntity the role of the user
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
     * @param personId   the id of the person
     * @param name       the name of the person
     * @param surname    the surname of the person
     * @param gender     the gender of the person
     * @param birthDate  the birthDate of the person
     * @param userId     the id of the user
     * @param password   the password of the user
     * @param roleEntity the role of the user
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
     * @param personId   the id of the person
     * @param name       the name of the person
     * @param surname    the surname of the person
     * @param gender     the gender of the person
     * @param birthDate  the birthDate of the person
     * @param userId     the id of the user
     * @param password   the password of the user
     * @param roleEntity the role of the user
     * @return a UserEntity
     * @throws NullPointerException if name, surname, password or
     *                              roleEntity is null (personId and userId can be
     *                              because of auto-increment)
     */
    public static UserEntity of(int personId, String name, String surname, Gender gender, String birthDate, int userId,
                                String password, RoleEntity roleEntity) {
        return new UserEntity(personId, name, surname, gender.getName(), birthDate, userId, password, roleEntity);
    }

    /**
     * Return a UserEntity
     *
     * @param userEntity the userEntity to copy
     * @return a UserEntity
     */
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
     * @param password the password to set
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
     * @param roleEntity the roleEntity to set
     * @throws NullPointerException if roleEntity is null
     */
    public void setUserRoleEntity(RoleEntity roleEntity) {
        requireNonNull(roleEntity);
        this.roleEntity = roleEntity;
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
     * Set the name
     *
     * @param name the name to set
     * @throws NullPointerException if name is null
     */
    public void setPersonName(String name) {
        requireNonNull(name);
        personEntity.setPersonName(name);
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
     * Set the surname
     *
     * @param surname the surname to set
     * @throws NullPointerException if surname is null
     */
    public void setPersonSurname(String surname) {
        requireNonNull(surname);
        personEntity.setPersonSurname(surname);
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
     */
    public Gender getPersonGender() {
        return personEntity.getPersonGender();
    }

    /**
     * Get the person birthdate
     */
    public String getPersonBirthDate() {
        return personEntity.getPersonBirthDate();
    }

    /**
     * Return the PersonEntity
     *
     * @return personEntity
     */
    public PersonEntity getPersonEntity() {
        return personEntity;
    }

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
