package src.fr.astro.entity;

import static java.util.Objects.requireNonNull;

/**
 * Participant
 * 
 * @see PersonEntity
 */
public class ParticipantEntity {

    private PersonEntity personEntity;
    private int participantId;
    private String category;
    private boolean present;

    /**
     * Constructor
     * 
     * @param userId
     * @param name
     * @param surname
     * @param participantId
     * @param category
     * @param present
     * @throws NullPointerException if personEntity, name, surname, participantId,
     */
    private ParticipantEntity(PersonEntity personEntity, int participantId, String category,
            boolean present) {

        requireNonNull(personEntity);
        requireNonNull(participantId);
        requireNonNull(category);
        requireNonNull(present);

        this.personEntity = personEntity;
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
     * @throws NullPointerException if userId, name, surname, participantId,
     *                              category or present is null
     */
    public static ParticipantEntity of(PersonEntity personEntity, int participantId, String category,
            boolean present) {
        return new ParticipantEntity(
                personEntity,
                participantId,
                category,
                present);
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
    public String getCategory() {
        return category;
    }

    /**
     * Return the present
     * 
     * @return present
     */
    public boolean isPresent() {
        return present;
    }

    /**
     * Set the category
     * 
     * @param category
     * @throws NullPointerException if category is null
     */
    public void setCategory(String category) {

        requireNonNull(category);

        this.category = category;
    }

    /**
     * Set the present
     * 
     * @param present
     * @throws NullPointerException if present is null
     */
    public void setPresent(boolean present) {

        requireNonNull(present);

        this.present = present;
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
        if (!super.equals(o))
            return false;

        ParticipantEntity that = (ParticipantEntity) o;

        if (getParticipantId() != that.getParticipantId())
            return false;
        if (isPresent() != that.isPresent())
            return false;
        return getCategory().equals(that.getCategory());
    }

    /**
     * Returns a hash code value for the Participant.
     * 
     * @return a hash code value for this Participant.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(this.participantId);
    }

}
