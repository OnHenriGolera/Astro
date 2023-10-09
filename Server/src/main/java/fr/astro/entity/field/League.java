package fr.astro.entity.field;

import java.util.Set;

public class League extends OptionalField {

    private static final Set<String> elementsSet = Set.of(
            "Nouvelle-Aquitaine",
            "Occitanie"); // TODO

    public League(String name) {
        super(name);

        elements = elementsSet;

        if (!isElement(name)) {
            setDefaultElement();
        }

    }

    public static League of(String name) {
        return new League(name);
    }

    public static String randomElement() {
        int random = (int) (Math.random() * (elementsSet.size() - 1));

        int i = 0;

        for (String leagueName : elementsSet) {

            if (i == random) {
                return leagueName;
            }

            i++;

        }

        return null;
    }

    @Override
    public String getFieldName() {
        return "League";
    }

    @Override
    protected void setDefaultElement() {
        this.name = "Unknown";
    }

}
