package fr.astro.util;

// import com.github.javafaker.Faker;

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

}
