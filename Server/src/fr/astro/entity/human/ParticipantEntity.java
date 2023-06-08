package fr.astro.entity.human;

import static java.util.Objects.requireNonNull;

/**
 * Participant
 * 
 * @see PersonEntity
 */
public class ParticipantEntity extends PersonEntity {

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
     * @throws NullPointerException if name, surname, participantId, category or
     *                              present is null (personId can be
     *                              because of auto-increment)
     */
    private ParticipantEntity(int personId, String name, String surname, int participantId, String category,
            boolean present) {

        super(personId, name, surname);

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
                ", personId=" + personId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
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

        return participantId == that.participantId
                && category.equals(that.category)
                && present == that.present
                && this.hashCode() == that.hashCode();
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
