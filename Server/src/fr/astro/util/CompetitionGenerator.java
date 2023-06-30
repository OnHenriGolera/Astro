package fr.astro.util;

import fr.astro.dao.competition.FormulaDAO;

// import com.github.javafaker.Faker;

import fr.astro.dao.competition.FormulaElementDAO;
import fr.astro.dao.competition.StageDAO;
import fr.astro.entity.competition.FormulaElementEntity;
import fr.astro.entity.competition.FormulaEntity;
import fr.astro.entity.competition.StageEntity;

public class CompetitionGenerator implements Instantiable {
    
    // Instance
    private static CompetitionGenerator instance;
    // private Faker fakerInstance;
    // private HumanGenerator humanGeneratorInstance = HumanGenerator.getInstance();

    /**
     * Constructor
     */
    private CompetitionGenerator() {
        // fakerInstance = new Faker();
    }

    /**
     * Return a Generator
     * Create a new Generator if instance is null
     * 
     * @return a Generator
     */
    public static CompetitionGenerator getInstance() {
        if (instance == null) {
            instance = new CompetitionGenerator();
        }
        return instance;
    }

    /**
     * Generate a type
     * 
     * @return a type
     */
    public String generateFormulaEntityType() {
        return "type"; // TODO
    }

    /**
     * Generate a numberBefore
     * 
     * @return a numberBefore
     */
    public int generateFormulaElementNumberBefore() {
        return 6; // TODO
    }

    /**
     * Generate a numberAfter
     * 
     * @return a numberAfter
     */
    public int generateFormulaElementNumberAfter() {
        return 1; // TODO
    }

    /**
     * Generate a description
     * 
     * @return a description
     */
    public String generateFormulaElementDescription() {
        return "description"; // TODO
    }

    /**
     * Generate a Name for a formula
     * @return
     */
    public String generateFormulaName() {
        return "name"; // TODO
    }

    /**
     * Generate a Description for a formula
     * @return
     */
    public String generateFormulaDescription() {
        return "description"; // TODO
    }

    /**
     * Generate a Name for a stage
     * @return
     */
    public String generateStageName() {
        return "name"; // TODO
    }

    
    /**
     * Return a FormulaElementEntity
     * 
     * @return a FormulaElementEntity
     */
    public FormulaElementEntity generateFormulaElementEntity() throws Exception {
        int formulaElementId = FormulaElementDAO.getInstance().getLastInsertedId() + 1;

        String type = generateFormulaEntityType();
        int numberBefore = generateFormulaElementNumberBefore();
        int numberAfter = generateFormulaElementNumberAfter();
        String description = generateFormulaElementDescription();

        return FormulaElementEntity.of(formulaElementId, type, numberBefore, numberAfter, description);
    }

    /**
     * Return a FormulaEntity
     * 
     * @return a FormulaEntity
     */
    public FormulaEntity generateFormulaEntity() throws Exception {
        int formulaId = FormulaDAO.getInstance().getLastInsertedId() + 1;

        String name = generateFormulaName();
        String description = generateFormulaDescription();

        return FormulaEntity.of(formulaId, name, description);
    }

    /**
     * Return a StageEntity
     * 
     * @return a StageEntity
     */
    public StageEntity generateStageEntity() throws Exception {

        int stageId = StageDAO.getInstance().getLastInsertedId() + 1;

        String name = generateStageName();
        FormulaEntity formula = generateFormulaEntity();
        
        // Save formula
        FormulaDAO.getInstance().save(formula);

        return StageEntity.of(stageId, name, formula);
    }

}
