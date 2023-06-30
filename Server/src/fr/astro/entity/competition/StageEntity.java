package fr.astro.entity.competition;

import java.util.Set;

import fr.astro.entity.human.ParticipantEntity;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;

/**
 * StageEntity
 * 
 * Represents a stage of a competition
 */
public class StageEntity {

    // Attributes
    private int id;
    private String name;
    private FormulaEntity formulaEntity;
    private Set<ParticipantEntity> participantEntities;

    /**
     * Constructor
     * 
     * @param id
     * @param name
     * @param formulaEntity
     * @param participantEntities
     */
    private StageEntity(int id, String name, FormulaEntity formulaEntity,
            Set<ParticipantEntity> participantEntities) {
        this.id = id;
        this.name = name;
        this.formulaEntity = formulaEntity;
        this.participantEntities = participantEntities;
    }

    /**
     * Constructor
     * 
     * @param id
     * @param name
     * @param formulaEntity
     * @return
     */
    private StageEntity(int id, String name, FormulaEntity formulaEntity) {
        this.id = id;
        this.name = name;
        this.formulaEntity = formulaEntity;
        this.participantEntities = new HashSet<>();
    }

    /**
     * Returns a StageEntity
     * 
     * @param id
     * @param name
     * @param formulaEntity
     * @param participantEntities
     * @return StageEntity
     */
    public static StageEntity of(int id, String name, FormulaEntity formulaEntity,
            Set<ParticipantEntity> participantEntities) {

        requireNonNull(name);
        requireNonNull(formulaEntity);
        requireNonNull(participantEntities);

        return new StageEntity(id, name, formulaEntity, participantEntities);
    }

    /**
     * Returns a StageEntity
     * 
     * @param id
     * @param name
     * @param formulaEntity
     * @return StageEntity
     */
    public static StageEntity of(int id, String name, FormulaEntity formulaEntity) {

        requireNonNull(name);
        requireNonNull(formulaEntity);

        return new StageEntity(id, name, formulaEntity);

    }

    /**
     * Returns the id
     * 
     * @return id
     */
    public int getStageId() {
        return id;
    }

    /**
     * Returns the name
     * 
     * @return name
     */
    public String getStageName() {
        return name;
    }

    /**
     * Returns the formulaEntity
     * 
     * @return formulaEntity
     */
    public FormulaEntity getFormulaEntity() {
        return formulaEntity;
    }

    /**
     * Returns the participantEntities
     * 
     * @return participantEntities
     */
    public Set<ParticipantEntity> getStageParticipantEntities() {
        return participantEntities;
    }

    /**
     * Adds a participantEntity
     * 
     * @param participantEntity
     */
    public boolean addStageParticipantEntity(ParticipantEntity participantEntity) {
        requireNonNull(participantEntity);
        return this.participantEntities.add(participantEntity);
    }

    /**
     * Removes a participantEntity
     * 
     * @param participantEntity
     */
    public boolean removeStageParticipantEntity(ParticipantEntity participantEntity) {
        requireNonNull(participantEntity);
        return this.participantEntities.remove(participantEntity);
    }

    /**
     * Set the name
     * 
     * @param name
     */
    public void setStageName(String name) {
        requireNonNull(name);
        this.name = name;
    }

    /**
     * Set the formulaEntity
     * 
     * @param formulaEntity
     */
    public void setStageFormulaEntity(FormulaEntity formulaEntity) {
        requireNonNull(formulaEntity);
        this.formulaEntity = formulaEntity;
    }

    /**
     * Set the participantEntities
     * 
     * @param participantEntities
     */
    public void setStageParticipantEntities(Set<ParticipantEntity> participantEntities) {
        requireNonNull(participantEntities);
        this.participantEntities = participantEntities;
    }
    
    /**
     * Returns a String representation of the object
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "StageEntity [formulaEntity=" + formulaEntity + ", id=" + id + ", name=" + name
                + ", participantEntities=" + participantEntities + "]";
    }

    /**
     * Returns a hash code for the object
     * 
     * @return int
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    /**
     * Indicates whether some other object is "equal to" this one
     * 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StageEntity) {
            StageEntity stageEntity = (StageEntity) obj;

            return stageEntity.id == id
                    && stageEntity.name.equals(name)
                    && stageEntity.formulaEntity.equals(formulaEntity)
                    && stageEntity.participantEntities.equals(participantEntities)
                    && this.hashCode() == stageEntity.hashCode();
        }
        return false;
    }

}
