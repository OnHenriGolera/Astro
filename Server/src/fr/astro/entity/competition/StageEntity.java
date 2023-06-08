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
    private FormulaElementEntity formulaElementEntity;
    private Set<ParticipantEntity> participantEntities;

    /**
     * Constructor
     * 
     * @param id
     * @param name
     * @param formulaElementEntity
     * @param participantEntities
     */
    private StageEntity(int id, String name, FormulaElementEntity formulaElementEntity,
            Set<ParticipantEntity> participantEntities) {
        this.id = id;
        this.name = name;
        this.formulaElementEntity = formulaElementEntity;
        this.participantEntities = participantEntities;
    }

    /**
     * Constructor
     * 
     * @param id
     * @param name
     * @param formulaElementEntity
     * @return
     */
    private StageEntity(int id, String name, FormulaElementEntity formulaElementEntity) {
        this.id = id;
        this.name = name;
        this.formulaElementEntity = formulaElementEntity;
        this.participantEntities = new HashSet<>();
    }

    /**
     * Returns a StageEntity
     * 
     * @param id
     * @param name
     * @param formulaElementEntity
     * @param participantEntities
     * @return StageEntity
     */
    public static StageEntity of(int id, String name, FormulaElementEntity formulaElementEntity,
            Set<ParticipantEntity> participantEntities) {

        requireNonNull(name);
        requireNonNull(formulaElementEntity);
        requireNonNull(participantEntities);

        return new StageEntity(id, name, formulaElementEntity, participantEntities);
    }

    /**
     * Returns a StageEntity
     * 
     * @param id
     * @param name
     * @param formulaElementEntity
     * @return StageEntity
     */
    public static StageEntity of(int id, String name, FormulaElementEntity formulaElementEntity) {

        requireNonNull(name);
        requireNonNull(formulaElementEntity);

        return new StageEntity(id, name, formulaElementEntity);

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
     * Returns the formulaElementEntity
     * 
     * @return formulaElementEntity
     */
    public FormulaElementEntity getFormulaElementEntity() {
        return formulaElementEntity;
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
     * Returns a String representation of the object
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "StageEntity [formulaElementEntity=" + formulaElementEntity + ", id=" + id + ", name=" + name
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
                    && stageEntity.formulaElementEntity.equals(formulaElementEntity)
                    && stageEntity.participantEntities.equals(participantEntities)
                    && this.hashCode() == stageEntity.hashCode();
        }
        return false;
    }

}
