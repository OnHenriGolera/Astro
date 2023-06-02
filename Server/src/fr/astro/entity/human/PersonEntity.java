package src.fr.astro.entity.human;

import static java.util.Objects.requireNonNull;

/**
 * PersonEntity
 */
public class PersonEntity {

    private int personId;
    private String name;
    private String surname;

    /**
     * Constructor
     * 
     * @param personId
     * @param name
     * @param surname
     * @throws NullPointerException if name or surname is null (personId can be
     *                              because of auto-increment)
     */
    public PersonEntity(int personId, String name, String surname) {

        requireNonNull(name);
        requireNonNull(surname);

        this.personId = personId;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Return a PersonEntity
     * 
     * @param personId - required
     * @param name     - required
     * @param surname  - required
     * @return a PersonEntity
     * @throws NullPointerException if name or surname is null (personId can be
     *                              because of auto-increment)
     */
    public static PersonEntity of(int personId, String name, String surname) {
        return new PersonEntity(personId, name, surname);
    }

    /**
     * Return the personId
     * 
     * @return personId
     */
    public int getPersonId() {
        return personId;
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
     * Set the personId
     * 
     * @param personId
     * @return void
     * @throws NullPointerException if personId is null
     */
    public void setPersonId(int personId) {

        requireNonNull(personId);

        this.personId = personId;
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
                "id=" + personId +
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
            return this.personId == other.personId;
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
        return Integer.hashCode(this.personId);
    }

}