package fr.astro.entity.human;

import static java.util.Objects.requireNonNull;

/**
 * Participant
 * 
 * @see PersonEntity
 */
public class ParticipantEntity {

    private int participantId;
    private String category;
    private boolean present;
    private PersonEntity personEntity;

    /**
     * Constructor
     * 
     * @param userId
     * @param name
     * @param surname
     * @param participantId
     * @param category
     * @param present
     * @throws NullPointerException if name, surname, participantId, category or
     *                              present is null (personId can be
     *                              because of auto-increment)
     */
    private ParticipantEntity(int personId, String name, String surname, int participantId, String category,
            boolean present) {

        this.personEntity = PersonEntity.of(personId, name, surname);

        requireNonNull(category);
        requireNonNull(present);

        this.participantId = participantId;
        this.category = category;
        this.present = present;
    }

    /**
     * Return a Participant
     * 
     * @param personEntity
     * @param participantId
     * @param category
     * @param present
     * @return a Participant
     * @throws NullPointerException if name, surname, participantId, category or
     *                              present is null (personId can be
     *                              because of auto-increment)
     */
    public static ParticipantEntity of(int personId, String name, String surname, int participantId, String category,
            boolean present) {
        return new ParticipantEntity(personId, name, surname, participantId, category, present);
    }

    /**
     * Return the participantId
     * 
     * @return participantId
     */
    public int getParticipantId() {
        return participantId;
    }

    /**
     * Return the category
     * 
     * @return category
     */
    public String getParticipantCategory() {
        return category;
    }

    /**
     * Return the present
     * 
     * @return present
     */
    public boolean isParticipantPresent() {
        return present;
    }

    /**
     * Set the category
     * 
     * @param category
     * @throws NullPointerException if category is null
     */
    public void setParticipantCategory(String category) {

        requireNonNull(category);

        this.category = category;
    }

    /**
     * Set the present
     * 
     * @param present
     * @throws NullPointerException if present is null
     */
    public void setParticipantPresent(boolean present) {

        requireNonNull(present);

        this.present = present;
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
     * Set the name
     * 
     * @param name
     */
    public void setPersonName(String name) {
        personEntity.setPersonName(name);
    }

    /**
     * Set the surname
     * 
     * @param surname
     */
    public void setPersonSurname(String surname) {
        personEntity.setPersonSurname(surname);
    }

    /**
     * Return the personEntity
     * 
     * @return personEntity
     */
    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    /**
     * Return a String representation of Participant
     * 
     * @return a String representation of Participant
     */
    @Override
    public String toString() {
        return "Participant{" +
                "participantId=" + participantId +
                ", category='" + category + '\'' +
                ", present=" + present +
                ", personEntity=" + personEntity +
                '}';
    }

    /**
     * Indicates whether some other Participant is "equal to" this one.
     * 
     * @param o - the reference object with which to compare.
     * @return true if this Participant is the same as the o argument; false
     *         otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ParticipantEntity))
            return false;

        ParticipantEntity participant = (ParticipantEntity) o;

        // Check each field
        return this.participantId == participant.participantId
                && this.category.equals(participant.category)
                && this.present == participant.present
                && this.personEntity.equals(participant.personEntity);

    }

    /**
     * Returns a hash code value for the Participant.
     * 
     * @return a hash code value for this Participant.
     */
    @Override
    public int hashCode() {

        // Hashcode has to be according to equals()
        return personEntity.hashCode()
                + participantId
                + category.hashCode()
                + (present ? 1 : 0);

    }

}
