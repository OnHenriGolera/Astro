package fr.astro.entity.human;

import static java.util.Objects.requireNonNull;

import fr.astro.entity.field.Category;
import fr.astro.entity.field.Club;
import fr.astro.entity.field.League;
import fr.astro.entity.field.Nationality;

/**
 * Participant
 *
 * @see PersonEntity
 */
public class ParticipantEntity {

    private final int participantId;
    private final PersonEntity personEntity;
    private final String license;
    private final int initialLocalRanking;
    private final int initialInternationalRanking;
    private final League league;
    private final Club club;
    private final Nationality nationality;
    private Category category;
    private boolean present;

    /**
     * Constructor
     *
     * @param personId                    the id of the person
     * @param name                        the name of the person
     * @param surname                     the surname of the person
     * @param gender                      the gender of the person
     * @param birthDate                   the birthDate of the person
     * @param participantId               the id of the participant
     * @param category                    the category of the participant
     * @param present                     the presence of the participant
     * @param license                     the license of the participant
     * @param initialLocalRanking         the initial local ranking of the participant
     * @param initialInternationalRanking the initial international ranking of the participant
     * @param league                      the league of the participant
     * @param club                        the club of the participant
     * @param nationality                 the nationality of the participant
     * @throws NullPointerException if the name, surname, participantId, category or
     *                              present is null (personId can be
     *                              because of auto-increment)
     */
    private ParticipantEntity(int personId,
                              String name,
                              String surname,
                              String gender,
                              String birthDate,
                              int participantId,
                              String category,
                              boolean present,
                              String license,
                              int initialLocalRanking,
                              int initialInternationalRanking,
                              String league,
                              String club,
                              String nationality) {

        this.personEntity = PersonEntity.of(personId, name, surname, gender, birthDate);

        requireNonNull(category);

        if (initialInternationalRanking == -1) {
            initialInternationalRanking = 10001;
        }

        if (initialLocalRanking == -1) {
            initialLocalRanking = 10001;
        }

        this.participantId = participantId;
        this.category = Category.of(category);
        this.present = present;
        this.license = license;
        this.initialLocalRanking = initialLocalRanking;
        this.initialInternationalRanking = initialInternationalRanking;
        this.league = League.of(league);
        this.club = Club.of(club);
        this.nationality = Nationality.of(nationality);
    }

    /**
     * Return a Participant
     *
     * @param personId                    the id of the person
     * @param name                        the name of the person
     * @param surname                     the surname of the person
     * @param gender                      the gender of the person
     * @param birthDate                   the birthDate of the person
     * @param participantId               the id of the participant
     * @param category                    the category of the participant
     * @param present                     the presence of the participant
     * @param license                     the license of the participant
     * @param initialLocalRanking         the initial local ranking of the participant
     * @param initialInternationalRanking the initial international ranking of the participant
     * @param league                      the league of the participant
     * @param club                        the club of the participant
     * @param nationality                 the nationality of the participant
     * @return a Participant
     * @throws NullPointerException if the name, surname, participantId, category or
     *                              present is null (personId can be
     *                              because of auto-increment)
     */
    public static ParticipantEntity of(int personId,
                                       String name,
                                       String surname,
                                       String gender,
                                       String birthDate,
                                       int participantId,
                                       String category,
                                       boolean present,
                                       String license,
                                       int initialLocalRanking,
                                       int initialInternationalRanking,
                                       String league,
                                       String club,
                                       String nationality) {
        return new ParticipantEntity(personId, name, surname, gender, birthDate, participantId, category, present,
                license,
                initialLocalRanking, initialInternationalRanking, league, club, nationality);
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
    public Category getParticipantCategory() {
        return category;
    }

    /**
     * Set the category
     *
     * @param category the category of the participant
     * @throws NullPointerException if category is null
     */
    public void setParticipantCategory(Category category) {

        requireNonNull(category);

        this.category = category;
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
     * Set the present
     *
     * @param present the presence of the participant
     * @throws NullPointerException if present is null
     */
    public void setParticipantPresent(boolean present) {

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
     * Set the name
     *
     * @param name the name of the person
     */
    public void setPersonName(String name) {
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
     * @param surname the surname of the person
     */
    public void setPersonSurname(String surname) {
        personEntity.setPersonSurname(surname);
    }

    /**
     * Return the license
     *
     * @return license
     */
    public String getParticipantLicense() {
        return license;
    }

    /**
     * Return the initialLocalRanking
     *
     * @return initialLocalRanking
     */
    public int getParticipantInitialLocalRanking() {
        return initialLocalRanking;
    }

    /**
     * Return the initialInternationalRanking
     *
     * @return initialInternationalRanking
     */
    public int getParticipantInitialInternationalRanking() {
        return initialInternationalRanking;
    }

    /**
     * Return the league
     *
     * @return league
     */
    public League getParticipantLeague() {
        return league;
    }

    /**
     * Return the club
     *
     * @return club
     */
    public Club getParticipantClub() {
        return club;
    }

    /**
     * Return the nationality
     */
    public Nationality getParticipantNationality() {
        return nationality;
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
     * otherwise.
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
                && this.personEntity.equals(participant.personEntity) // TODO change access
                && this.license.equals(participant.license)
                && this.initialLocalRanking == participant.initialLocalRanking
                && this.initialInternationalRanking == participant.initialInternationalRanking
                && this.league.equals(participant.league)
                && this.club.equals(participant.club)
                && this.nationality.equals(participant.nationality);

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
                + (present ? 1 : 0)
                + license.hashCode()
                + initialLocalRanking
                + initialInternationalRanking
                + league.hashCode()
                + club.hashCode()
                + nationality.hashCode();

    }

}
