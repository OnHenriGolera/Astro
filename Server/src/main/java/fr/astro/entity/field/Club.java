package fr.astro.entity.field;

import java.util.Set;

public class Club extends OptionalField {

    private static final Set<String> elementsSet = Set.of(
            "poitiers",
            "paris",
            "lyon");

    public Club(String name) {
        super(name);

        elements = elementsSet;

        if (!isElement(name)) {
            setDefaultElement();
        }

    }

    public static Club of(String name) {
        return new Club(name);
    }

    public static String randomElement() {
        int random = (int) (Math.random() * (elementsSet.size() - 1));

        int i = 0;

        for (String clubName : elementsSet) {

            if (i == random) {
                return clubName;
            }

            i++;

        }

        return null;
    }

    @Override
    public String getFieldName() {
        return "Club";
    }

    @Override
    protected void setDefaultElement() {
        this.name = "Unknown";
    }

}
