package fr.astro.test.content.competition.formulaElementEntity;

import fr.astro.dao.SQLObject;
import fr.astro.dao.competition.FormulaElementDAO;
import fr.astro.entity.competition.FormulaElementEntity;
import fr.astro.test.specialized.SQLObjectTest;
import fr.astro.util.CompetitionGenerator;

public class FormulaElementEntityFullTest extends SQLObjectTest<FormulaElementEntity> {

    private CompetitionGenerator competitionGeneratorInstance = CompetitionGenerator.getInstance();
    private final static boolean display = true;

    /**
     * Main
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        FormulaElementEntityFullTest test = new FormulaElementEntityFullTest();

        test.test(display);

    }

    /**
     * Constructor
     *
     * Set the name of the test
     */
    public FormulaElementEntityFullTest() {

        testName = "FormulaElementEntityFullTest";

    }

    @Override
    public FormulaElementEntity generateObject() throws Exception {
        return competitionGeneratorInstance.generateFormulaElementEntity();
    }

    @Override
    public int getIdFromObject(FormulaElementEntity object) {
        return object.getFormulaElementId();
    }

    @Override
    public SQLObject<FormulaElementEntity> getDao() {
        return FormulaElementDAO.getInstance();
    }

    @Override
    public void modifyObject(FormulaElementEntity object) {
        object.setFormulaElementDescription("new description");
        try {
            object.setFormulaElementNumberAfter(2);
            object.setFormulaElementNumberBefore(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
