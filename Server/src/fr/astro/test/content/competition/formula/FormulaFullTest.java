package fr.astro.test.content.competition.formula;

import fr.astro.dao.SQLObject;
import fr.astro.dao.competition.FormulaDAO;
import fr.astro.entity.competition.FormulaEntity;
import fr.astro.test.specialized.SQLObjectTest;
import fr.astro.util.CompetitionGenerator;

public class FormulaFullTest extends SQLObjectTest<FormulaEntity> {

    private CompetitionGenerator competitionGeneratorInstance = CompetitionGenerator.getInstance();
    private final static boolean display = true;

    /**
     * Main
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        FormulaFullTest test = new FormulaFullTest();

        test.test(display);

    }

    /**
     * Constructor
     *
     * Set the name of the test
     */
    public FormulaFullTest() {

        testName = "FormulaFullTest";

    }

    @Override
    public FormulaEntity generateObject() throws Exception {
        return competitionGeneratorInstance.generateFormulaEntity();
    }

    @Override
    public int getIdFromObject(FormulaEntity object) {
        return object.getFormulaId();
    }

    @Override
    public SQLObject<FormulaEntity> getDao() {
        return FormulaDAO.getInstance();
    }

    @Override
    public void modifyObject(FormulaEntity object) {
        object.setFormulaDescription(competitionGeneratorInstance.generateFormulaDescription());
        object.setFormulaName(competitionGeneratorInstance.generateFormulaName());
    }

}
