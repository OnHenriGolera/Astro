package fr.astro.entity.human;

import static java.util.Objects.requireNonNull;

import fr.astro.entity.field.Gender;

/**
 * PersonEntity
 */
public class PersonEntity {

    private int personId;
    private String name;
    private String surname;
    private Gender gender;
    private String birthDate;

    /**
     * Constructor
     * 
     * @param personId
     * @param name
     * @param surname
     * @throws NullPointerException if name or surname is null (personId can be
     *                              because of auto-increment)
     */
    public PersonEntity(int personId, String name, String surname, String gender, String birthDate) {

        requireNonNull(name);
        requireNonNull(surname);
        requireNonNull(gender);

        this.personId = personId;
        this.name = name;
        this.surname = surname;
        this.gender = Gender.of(gender);
        this.birthDate = birthDate;
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
    public static PersonEntity of(int personId, String name, String surname, String gender, String birthDate) {
        return new PersonEntity(personId, name, surname, gender, birthDate);
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
     * Get the gender
     * 
     * @return gender
     */
    public Gender getPersonGender() {
        return gender;
    }

    /**
     * Get the birthDate
     * 
     * @param birthDate
     */
    public String getPersonBirthDate() {
        return birthDate;
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

        // Check each field
        return personEntity.personId == this.personId
                && personEntity.name.equals(this.name)
                && personEntity.surname.equals(this.surname)
                && personEntity.gender.equals(this.gender)
                && personEntity.birthDate.equals(this.birthDate);
    }

    /**
     * Return a hashcode
     * 
     * @return a hashcode
     * @see PersonEntity
     */
    @Override
    public int hashCode() {

        // Hashcode has to be according to equals
        return 31 * (personId
                + name.hashCode()
                + surname.hashCode())
                + gender.hashCode()
                + birthDate.hashCode();

    }

}
