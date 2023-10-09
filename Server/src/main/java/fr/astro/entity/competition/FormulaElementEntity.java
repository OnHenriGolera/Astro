package fr.astro.entity.competition;

import static java.util.Objects.requireNonNull;

import fr.astro.exception.InvalidArguments;

public class FormulaElementEntity {

    private final int formulaElementId;
    private String type;
    private int numberBefore;
    private int numberAfter;
    private String description;

    /**
     * Constructor
     *
     * @param formulaElementId the id of the formulaElement
     * @param type             the type of the formulaElement
     * @param numberBefore     the number of player entering the formulaElement
     * @param numberAfter      the number of player qualifying to the next formulaElement
     * @param description      the description of the formulaElement
     * @throws InvalidArguments if numberBefore or numberAfter is negative
     */
    public FormulaElementEntity(int formulaElementId, String type, int numberBefore, int numberAfter,
                                String description) throws InvalidArguments {

        requireNonNull(type);
        requireNonNull(description);

        if (numberBefore < 0 || numberAfter < 0) {
            throw new InvalidArguments("numberBefore and numberAfter must be positive");
        }

        this.formulaElementId = formulaElementId;
        this.type = type;
        this.numberBefore = numberBefore;
        this.numberAfter = numberAfter;
        this.description = description;
    }

    /**
     * Return a FormulaElementEntity
     *
     * @param formulaElementId the id of the formulaElement
     * @param type             the type of the formulaElement
     * @param numberBefore     the number of player entering the formulaElement
     * @param numberAfter      the number of player qualifying to the next formulaElement
     * @param description      the description of the formulaElement
     * @return a FormulaElementEntity
     * @throws InvalidArguments if numberBefore or numberAfter is negative
     */
    public static FormulaElementEntity of(int formulaElementId, String type, int numberBefore, int numberAfter,
                                          String description) throws InvalidArguments {
        return new FormulaElementEntity(formulaElementId, type, numberBefore, numberAfter, description);
    }

    /**
     * Return the formulaElementId
     *
     * @return formulaElementId
     */
    public int getFormulaElementId() {
        return formulaElementId;
    }

    /**
     * Return the type
     *
     * @return type
     */
    public String getFormulaElementType() {
        return type;
    }

    /**
     * Set the type
     *
     * @param type the type of the formulaElement
     */
    public void setFormulaElementType(String type) {

        requireNonNull(type);

        this.type = type;
    }

    /**
     * Return the numberBefore
     *
     * @return numberBefore
     */
    public int getFormulaElementNumberBefore() {
        return numberBefore;
    }

    /**
     * Set the numberBefore
     *
     * @param numberBefore the number of player entering the formulaElement
     * @throws InvalidArguments if numberBefore is negative
     */
    public void setFormulaElementNumberBefore(int numberBefore) throws InvalidArguments {
        if (numberBefore < 0) {
            throw new InvalidArguments("numberBefore must be positive");
        }
        this.numberBefore = numberBefore;
    }

    /**
     * Return the numberAfter
     *
     * @return numberAfter
     */
    public int getFormulaElementNumberAfter() {
        return numberAfter;
    }

    /**
     * Set the numberAfter
     *
     * @param numberAfter the number of player qualifying to the next formulaElement
     * @throws InvalidArguments if numberAfter is negative
     */
    public void setFormulaElementNumberAfter(int numberAfter) throws InvalidArguments {
        if (numberAfter < 0) {
            throw new InvalidArguments("numberAfter must be positive");
        }
        this.numberAfter = numberAfter;
    }

    /**
     * Return the description
     *
     * @return description
     */
    public String getFormulaElementDescription() {
        return description;
    }

    /**
     * Set the description
     *
     * @param description the description of the formulaElement
     * @throws NullPointerException if description is null
     */
    public void setFormulaElementDescription(String description) {

        requireNonNull(description);

        this.description = description;
    }

    /**
     * Return the FormulaElementEntity as a String
     *
     * @return FormulaElementEntity as a String
     */
    @Override
    public String toString() {
        return "FormulaElementEntity [description=" + description + ", formulaElementId=" + formulaElementId
                + ", numberAfter=" + numberAfter + ", numberBefore=" + numberBefore + ", type=" + type + "]";
    }

    /**
     * Test if two FormulaElementEntity are equals
     *
     * @param obj the object to test
     * @return true if they are equals, false otherwise
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == this)
            return true;

        if (!(obj instanceof FormulaElementEntity))
            return false;

        FormulaElementEntity formulaElementEntity = (FormulaElementEntity) obj;

        // Check each attribute
        return formulaElementEntity.formulaElementId == formulaElementId
                && formulaElementEntity.type.equals(type)
                && formulaElementEntity.numberBefore == numberBefore
                && formulaElementEntity.numberAfter == numberAfter
                && formulaElementEntity.description.equals(description);
    }

    /**
     * Return the hashcode of the FormulaElementEntity
     *
     * @return the hashcode of the FormulaElementEntity
     */
    @Override
    public int hashCode() {

        // Hashcode has to be according to equals()
        return formulaElementId
                + type.hashCode()
                + numberBefore
                + numberAfter
                + description.hashCode();

    }

}
