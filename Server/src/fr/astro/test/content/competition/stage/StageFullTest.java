package fr.astro.test.content.competition.stage;

import fr.astro.dao.SQLObject;
import fr.astro.dao.competition.StageDAO;
import fr.astro.entity.competition.StageEntity;
import fr.astro.test.specialized.SQLObjectTest;
import fr.astro.util.CompetitionGenerator;

public class StageFullTest extends SQLObjectTest<StageEntity> {

    // Instance
    private static CompetitionGenerator competitionGeneratorInstance = CompetitionGenerator.getInstance();

    public static void main(String[] args) throws Exception {
        StageFullTest test = new StageFullTest();
        test.test(true);
    }

    /**
     * Constructor
     *
     * Set the name of the test
     */
    public StageFullTest() {
        testName = "StageFullTest";
    }

    @Override
    public StageEntity generateObject() throws Exception {
        return competitionGeneratorInstance.generateStageEntity();
    }

    @Override
    public int getIdFromObject(StageEntity object) {
        return object.getStageId();
    }

    @Override
    public SQLObject<StageEntity> getDao() {
        return StageDAO.getInstance();
    }

    @Override
    public void modifyObject(StageEntity object) {
        object.setStageName(competitionGeneratorInstance.generateStageName());
    }
    
}
