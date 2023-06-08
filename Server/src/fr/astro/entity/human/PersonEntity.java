package fr.astro.entity.human;

import static java.util.Objects.requireNonNull;

/**
 * PersonEntity
 */
public class PersonEntity {

    protected int personId;
    protected String name;
    protected String surname;

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

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof PersonEntity)) {
            return false;
        }

        PersonEntity personEntity = (PersonEntity) obj;

        return personEntity.personId == personId
                && personEntity.name.equals(name)
                && personEntity.surname.equals(surname)
                && personEntity.hashCode() == hashCode();
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
