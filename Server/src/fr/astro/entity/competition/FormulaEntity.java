package fr.astro.entity.competition;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * FormulaEntity
 * 
 * Represents a formula of a competition
 */
public class FormulaEntity {

    // Attributes
    private int formulaId;
    private String name;
    private String description;
    private Set<FormulaElementEntity> formulaElementEntities;

    /**
     * Constructor
     * 
     * @param formulaId
     * @param name
     * @param description
     * @param formulaElementEntities
     */
    private FormulaEntity(int formulaId, String name, String description,
            Set<FormulaElementEntity> formulaElementEntities) {
        this.formulaId = formulaId;
        this.name = name;
        this.description = description;
        this.formulaElementEntities = formulaElementEntities;
    }

    /**
     * Constructor
     * 
     * @param formulaId
     * @param name
     * @param description
     */
    private FormulaEntity(int formulaId, String name, String description) {
        this.formulaId = formulaId;
        this.name = name;
        this.description = description;
        this.formulaElementEntities = new HashSet<>();
    }

    /**
     * Returns a FormulaEntity
     * 
     * @param formulaId
     * @param name
     * @param description
     * @param formulaElementEntities
     * @return a FormulaEntity
     */
    public static FormulaEntity of(int formulaId, String name, String description,
            Set<FormulaElementEntity> formulaElementEntities) {

        requireNonNull(name);
        requireNonNull(description);
        requireNonNull(formulaElementEntities);

        return new FormulaEntity(formulaId, name, description, formulaElementEntities);
    }

    /**
     * Returns a FormulaEntity
     * 
     * @param formulaId
     * @param name
     * @param description
     * @return a FormulaEntity
     */
    public static FormulaEntity of(int formulaId, String name, String description) {

        requireNonNull(name);
        requireNonNull(description);

        return new FormulaEntity(formulaId, name, description);
    }

    /**
     * Returns the formulaId
     * 
     * @return the formulaId
     */
    public int getFormulaId() {
        return formulaId;
    }

    /**
     * Returns the name
     * 
     * @return the name
     */
    public String getFormulaName() {
        return name;
    }

    /**
     * Returns the description
     * 
     * @return the description
     */
    public String getFormulaDescription() {
        return description;
    }

    /**
     * Returns the formulaElementEntities
     * 
     * @return the formulaElementEntities
     */
    public Set<FormulaElementEntity> getFormulaElementEntities() {
        return formulaElementEntities;
    }

    /**
     * Add a formulaElementEntity
     * 
     * @param formulaElementEntity
     */
    public boolean addFormulaElementEntity(FormulaElementEntity formulaElementEntity) {
        requireNonNull(formulaElementEntity);
        return formulaElementEntities.add(formulaElementEntity);
    }

    /**
     * Remove a formulaElementEntity
     * 
     * @param formulaElementEntity
     */
    public boolean removeFormulaElementEntity(FormulaElementEntity formulaElementEntity) {
        requireNonNull(formulaElementEntity);
        return formulaElementEntities.remove(formulaElementEntity);
    }

    /**
     * Change the name
     * 
     * @param name
     */
    public void setFormulaName(String name) {
        requireNonNull(name);
        this.name = name;
    }

    /**
     * Change the description
     * 
     * @param description
     */
    public void setFormulaDescription(String description) {
        requireNonNull(description);
        this.description = description;
    }

    /**
     * Returns a String representation of a FormulaEntity
     *
     * @return a String representation of a FormulaEntity
     */
    @Override
    public String toString() {
        return "FormulaEntity [formulaId=" + formulaId + ", name=" + name + ", description=" + description
                + ", formulaElementEntities=" + formulaElementEntities + "]";
    }

    /**
     * Returns a hash code for this FormulaEntity
     * 
     * @return a hash code for this FormulaEntity
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = formulaId * prime;
        result = name.hashCode() * prime;
        result = description.hashCode() * prime;
        result = formulaElementEntities.hashCode() * prime;
        return result;
    }

    /**
     * Indicates whether some other FormulaEntity is "equal to" this one
     * 
     * @param obj
     * @return true if this FormulaEntity is the same as obj argument; false
     *         otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof FormulaEntity))
            return false;
        FormulaEntity other = (FormulaEntity) obj;

        return formulaId == other.formulaId
                && name.equals(other.name)
                && description.equals(other.description)
                && formulaElementEntities.equals(other.formulaElementEntities)
                && this.hashCode() == other.hashCode();
    }

}
