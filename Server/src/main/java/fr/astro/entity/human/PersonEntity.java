package fr.astro.entity.human;

import static java.util.Objects.requireNonNull;

import fr.astro.entity.field.Gender;

/**
 * PersonEntity
 */
public class PersonEntity {

    private int personId;
    private String personName;
    private String personSurname;
    private Gender personGender;
    private String personBirthDate;

    /**
     * Constructor
     *
     * @param personId
     * @param personName
     * @param personSurname
     * @throws NullPointerException if name or surname is null (personId can be
     *                              because of auto-increment)
     */
    public PersonEntity(int personId, String personName, String personSurname, String personGender, String personBirthDate) {

        requireNonNull(personName);
        requireNonNull(personSurname);
        requireNonNull(personGender);

        this.personId = personId;
        this.personName = personName;
        this.personSurname = personSurname;
        this.personGender = Gender.of(personGender);
        this.personBirthDate = personBirthDate;
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
        return personName;
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

        this.personName = name;
    }

    /**
     * Return the surname
     *
     * @return surname
     */
    public String getPersonSurname() {
        return personSurname;
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

        this.personSurname = surname;
    }

    /**
     * Get the gender
     *
     * @return gender
     */
    public Gender getPersonGender() {
        return personGender;
    }

    /**
     * Get the birthDate
     *
     * @param birthDate
     */
    public String getPersonBirthDate() {
        return personBirthDate;
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
                ", name='" + personName + '\'' +
                ", surname='" + personSurname + '\'' +
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
                && personEntity.personName.equals(this.personName)
                && personEntity.personSurname.equals(this.personSurname)
                && personEntity.personGender.equals(this.personGender)
                && personEntity.personBirthDate.equals(this.personBirthDate);
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
                + personName.hashCode()
                + personSurname.hashCode())
                + personGender.hashCode()
                + personBirthDate.hashCode();

    }

}
