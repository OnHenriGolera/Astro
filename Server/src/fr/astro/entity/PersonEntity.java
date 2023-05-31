package src.fr.astro.entity;

import static java.util.Objects.requireNonNull;

/**
 * PersonEntity
 */
public class PersonEntity {

    private int userId;
    private String name;
    private String surname;

    /**
     * Constructor
     * 
     * @param userId
     * @param name
     * @param surname
     * @throws NullPointerException if userId, name or surname is null
     */
    private PersonEntity(int userId, String name, String surname) {

        requireNonNull(name);
        requireNonNull(surname);
        requireNonNull(userId);

        this.userId = userId;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Return a PersonEntity
     * 
     * @param userId  - required
     * @param name    - required
     * @param surname - required
     * @return a PersonEntity
     * @throws NullPointerException if userId, name or surname is null
     */
    public static PersonEntity of(int userId, String name, String surname) {
        return new PersonEntity(userId, name, surname);
    }

    /**
     * Return the userId
     * 
     * @return userId
     */
    public int getPersonId() {
        return userId;
    }

    /**
     * Return the name
     * 
     * @return name
     */
    public String getPersonName() {
        return name;
    }

    /**
     * Return the surname
     * 
     * @return surname
     */
    public String getPersonSurname() {
        return surname;
    }

    /**
     * Set the userId
     * 
     * @param userId
     * @return void
     * @throws NullPointerException if userId is null
     */
    public void setPersonId(int userId) {

        requireNonNull(userId);

        this.userId = userId;
    }

    /**
     * Set the name
     * 
     * @param name
     * @return void
     * @throws NullPointerException if name is null
     */
    public void setPersonName(String name) {

        requireNonNull(name);

        this.name = name;
    }

    /**
     * Set the surname
     * 
     * @param surname
     * @return void
     * @throws NullPointerException if surname is null
     */
    public void setPersonSurname(String surname) {

        requireNonNull(surname);

        this.surname = surname;
    }

    /**
     * Return a String
     * 
     * @return a String representing the PersonEntity
     */
    @Override
    public String toString() {
        return "PersonEntity{" +
                "id=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    /**
     * Return a boolean if the object is equals to the PersonEntity
     * 
     * @param obj
     * @return a boolean
     * @see PersonEntity
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PersonEntity) {
            PersonEntity other = (PersonEntity) obj;
            return this.userId == other.userId;
        }
        return false;
    }

    /**
     * Return a hashcode
     * 
     * @return a hashcode
     * @see PersonEntity
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(this.userId);
    }

}
