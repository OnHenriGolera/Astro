package fr.astro.entity.competition;

import static java.util.Objects.requireNonNull;

import fr.astro.exception.InvalidArguments;

public class FormulaElementEntity {

    private int formulaElementId;
    private String type;
    private int numberBefore;
    private int numberAfter;
    private String description;

    /**
     * Constructor
     * 
     * @param formulaElementId
     * @param type
     * @param numberBefore
     * @param numberAfter
     * @param description
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
     * @param formulaElementId
     * @param type
     * @param numberBefore
     * @param numberAfter
     * @param description
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
     * Return the numberBefore
     * 
     * @return numberBefore
     */
    public int getFormulaElementNumberBefore() {
        return numberBefore;
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
     * Return the description
     * 
     * @return description
     */
    public String getFormulaElementDescription() {
        return description;
    }

    /**
     * Set the type
     * 
     * @param type
     */
    public void setFormulaElementType(String type) {

        requireNonNull(type);

        this.type = type;
    }

    /**
     * Set the numberBefore
     * 
     * @param numberBefore
     * @throws InvalidArguments if numberBefore is negative
     */
    public void setFormulaElementNumberBefore(int numberBefore) throws InvalidArguments {
        if (numberBefore < 0) {
            throw new InvalidArguments("numberBefore must be positive");
        }
        this.numberBefore = numberBefore;
    }

    /**
     * Set the numberAfter
     * 
     * @param numberAfter
     * @throws InvalidArguments if numberAfter is negative
     */
    public void setFormulaElementNumberAfter(int numberAfter) throws InvalidArguments {
        if (numberAfter < 0) {
            throw new InvalidArguments("numberAfter must be positive");
        }
        this.numberAfter = numberAfter;
    }

    /**
     * Set the description
     * 
     * @param description
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
     * @param obj
     * @return true if they are equals, false otherwise
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == this)
            return true;

        if (!(obj instanceof FormulaElementEntity))
            return false;

        FormulaElementEntity formulaElementEntity = (FormulaElementEntity) obj;

        return formulaElementEntity.formulaElementId == formulaElementId
                && formulaElementEntity.type.equals(type)
                && formulaElementEntity.numberBefore == numberBefore
                && formulaElementEntity.numberAfter == numberAfter
                && formulaElementEntity.description.equals(description)
                && formulaElementEntity.hashCode() == hashCode();
    }

    /**
     * Return the hashcode of the FormulaElementEntity
     * 
     * @return the hashcode of the FormulaElementEntity
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(formulaElementId);
    }

}
