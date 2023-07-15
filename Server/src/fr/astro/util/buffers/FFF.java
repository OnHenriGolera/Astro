package fr.astro.util.buffers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import fr.astro.entity.field.Category;
import fr.astro.entity.human.ParticipantEntity;
import fr.astro.exception.competition.InvalidFFFFile;
import fr.astro.util.Instantiable;

/**
 * Helps to use FFF files
 * 
 * @see Instantiable
 */
public class FFF {

    public static final String FFF_EXTENSION = ".fff";

    // ------------ REGEX ------------
    private final static String UNICODE_REGEX = "[\u0020'\\-A-Za-z\u00C1-\u00C2\u00C4\u00C7-\u00CB\u00CE-\u00CF\u00D4\u00D6\u00D9\u00DB-\u00DC\u00E0\u00E2\u00E4\u00E7-\u00EB\u00EE-\u00EF\u00F4\u00F6\u00F9\u00FB-\u00FC\u00FF ]";
    private final static String FIRST_LINE_REGEX = String.format("^fff;(win|dos)?;(classement|competition|sauvegarde)?;(%s{1,})?$", UNICODE_REGEX);
    private final static String SECOND_LINE_REGEX = String.format(
            "[0-9]{1,2}/[0-9]{1,2}/[0-9]{4};(fleuret|epee|sabre)?;(m|f)?;(%s{1,})?;(%s{1,})?(;(%s{1,})?)?$",
            UNICODE_REGEX, UNICODE_REGEX, UNICODE_REGEX);

    private final static String PARTICIPANT_REGEX = String.format(
            "(%s),(%s),([0-9]{1,2}/[0-9]{1,2}/[0-9]{4}),(m|f),([A-Z]{3})?;([A-Z]{3} [A-Z] [0-9]{8} [0-9]{2}),([0-9]{1,2}),([0-9]{1,3});([0-9]{1,2}),([0-9]{1,2});([0-9]{1,2}),([0-9]{1,2});",
            UNICODE_REGEX, UNICODE_REGEX);


    // Attributes
    private List<String> content;

    // From the first line
    private String charset;
    private String type;
    private String creator;

    // From the second line
    private String date;
    private String weapon;
    private String gender;
    private Category category;
    private String competitionName;
    private String competitionReducedName;

    /**
     * Constructor
     * 
     * @param file
     */
    public FFF(File file) throws InvalidFFFFile {

        if (!file.getName().toLowerCase().endsWith(FFF_EXTENSION)) {
            throw new InvalidFFFFile(file.getName(), "File extension is not '" + FFF_EXTENSION + "'");
        }

        // Get the content of the file
        try {
            content = FileReaderManagement.getInstance().getFileContent(file);
        } catch (Exception e) {
            throw new InvalidFFFFile(file.getName(), e.getMessage());
        }

        if (content == null || content.isEmpty()) {
            throw new InvalidFFFFile(file.getName(), "File is empty");
        }

        // Check the file
        checkFile(file);

    }

    /**
     * Check the file
     * 
     * @param file
     * @throws InvalidFFFFile
     */
    private void checkFile(File file) throws InvalidFFFFile {

        // Check the first line
        if (!checkFirstLine(content.get(0))) {
            throw new InvalidFFFFile(file.getName(), "First line is not valid");
        }

        // Check the second line
        if (!checkSecondLine(content.get(1))) {
            throw new InvalidFFFFile(file.getName(), "Second line is not valid");
        }

        System.out.println(PARTICIPANT_REGEX);
    }

    /**
     * Check the first line
     * 
     * @param line
     * @return true if the line is valid, false otherwise
     */
    private boolean checkFirstLine(String line) {

        /*
         * First line of FFF files has to be like this:
         * "11;12;13;14"
         * 
         * Where : 
         * 
         * - 11 is "FFF", indicating that the file is a FFF file
         * - 12 is the charset, between "WIN" and "DOS"
         * - 13 is the type of FFF information, between "CLASSEMENT", "COMPETITION" and "SAUVEGARDE"
         * - 14 is the person who created the file (more than 1 character)
         * 
         * Note : Lower of upper case doesn't matter, so regex in lower case
         * 
         * Example :
         * 
         * "FFF;WIN;CLASSEMENT;FIE"
         */

        // Check the line
        if (!line.toLowerCase().matches(FIRST_LINE_REGEX)) {
            return false;
        }

        // Get the data
        String[] data = line.split(";");
        charset = data[1];
        type = data[2];
        creator = data[3];

        return true;

    }
    
    /**
     * Check the second line
     * 
     * @param line
     * @return true if the line is valid, false otherwise
     */
    private boolean checkSecondLine(String line) {

        /*
         * Second line of FFF files has to be like this:
         * "21;22;23;24;25;26;"
         * 
         * Where :
         * 
         * - 21 is the date (ex 7/6/2000)
         * - 22 is the weapon between "fleuret", "epee", "sabre"
         * - 23 is the gender between "m" and "f"
         * - 24 is the category (check if it's valid with Category class)
         * - 25 is the name of the competition or ranking
         * - 26 is the reduced name of the competition or ranking
         * 
         * Note : Lower of upper case doesn't matter
         * 
         * Example :
         * 
         * 7/6/2000;sabre;M;Classement officiel de la FIE
         */

        // Check the line
        if (!Pattern.matches(SECOND_LINE_REGEX, line.toLowerCase())) {
            return false;
        }

        // Get the data
        String[] data = line.split(";");

        // Check the category
        try {
            category = new Category(data[3]);
        } catch (Exception e) {
            return false;
        }

        // Get the data
        try {
            date = data[0];
            weapon = data[1];
            gender = data[2];
            competitionName = data[4];
        } catch (Exception e) {
            return false;
        }

        // Check if there is a reduced name
        if (data.length == 6) {
            competitionReducedName = data[5];
        }

        return true;
    }

    /**
     * Get the Participants
     * 
     * @return the Participants
     */
    public List<ParticipantEntity> getParticipants() {

        /*
         * Participants infos are divided in 4 parts :
         * 
         * 1. General infos
         * 2. FIE infos
         * 3. National infos
         * 4. Competition infos
         * 
         * General infos are the same for all participants :
         * 
         * - G1 is the name
         * - G2 is the surname
         * - G3 is the birth date
         * - G4 is the gender
         * - G5 is the nationality
         * 
         * FIE infos are for international participants :
         * 
         * - I1 is the FIE licence number
         * - I2 is the FIE Rank
         * - I3 is the FIE points
         * 
         * National infos are for national participants :
         * 
         * - N1 is the national licence number
         * - N2 is the state or league
         * - N3 is the club
         * - N4 is the national rank
         * - N5 is the national points
         * - N6 is reserved, can be anything
         * 
         * Competition infos differ if it's a save, a save after qualifications, or a
         * competition :
         * 
         * For a rank :
         * 
         * - C1 is the final rank at the end of the competition
         * - C2 is where the participant lost, between "p" for pool, "t" for tableau
         * (bracket)
         * 
         * For a save after players are qualified :
         * 
         * - C1 is the rank in the qualification
         * - C2 is "q" for qualified
         * 
         * For a save
         * 
         * - C1 is the original rank in the pool or bracket
         * - C2 is either "e" for exempted, "p" for pool, "t" for tableau (bracket)
         * - C3 is the pool number
         * - C4 is the rank inside the pool
         * 
         * C3 and C4 are optional, there could be no C3 and C4 and so no ";"
         * 
         * We get a string like this :
         * 
         * "G1,G2,G3,G4,G5;I1,I2,I3;N1,N2,N3,N4,N5,N6;C1,C2,C3,C4;"
         * 
         * or
         * 
         * "G1,G2,G3,G4,G5;I1,I2,I3;N1,N2,N3,N4,N5,N6;C1,C2,C3,C4"
         * 
         * Example :
         * 
         * MARTIN,́Rene,2/10/1976,M,FRA;FRA M 02101976 00,2,251;;
         */

        // Get the participants
        List<ParticipantEntity> participants = new ArrayList<>();

        // Get the participants
        // for (int i = 2; i < content.size(); i++) {
            


        // }

        return participants;
    }
    
    /**
     * Get the charset
     * 
     * @return the charset
     */
    public String getCharset() {
        return charset;
    }

    /**
     * Get the type
     * 
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Get the creator
     * 
     * @return the creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Get the date
     * 
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Get the weapon
     * 
     * @return the weapon
     */
    public String getWeapon() {
        return weapon;
    }

    /**
     * Get the gender
     * 
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Get the category
     * 
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Get the competition name
     * 
     * @return the competition name
     */
    public String getCompetitionName() {
        return competitionName;
    }

    /**
     * Get the competition reduced name
     * 
     * @return the competition reduced name
     */
    public String getCompetitionReducedName() {
        return competitionReducedName;
    }

}
