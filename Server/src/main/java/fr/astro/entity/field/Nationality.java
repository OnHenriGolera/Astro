package fr.astro.entity.field;

import java.util.Set;

public class Nationality extends OptionalField {

    private static final Set<String> elementsSet = Set.of(
            "fra",
            "esp",
            "ita",
            "deu",
            "egy");

    public Nationality(String name) {
        super(name);

        elements = elementsSet;

        if (!isElement(name)) {
            setDefaultElement();
        }

    }

    public static Nationality of(String name) {
        return new Nationality(name);
    }

    public static String randomElement() {

        int random = (int) (Math.random() * (elementsSet.size() - 1));

        int i = 0;

        for (String nationalityName : elementsSet) {

            if (i == random) {
                return nationalityName;
            }

            i++;

        }

        return null;
    }

    @Override
    public String getFieldName() {
        return "Nationality";
    }

    @Override
    protected void setDefaultElement() {
        this.name = "Unknown";
    }

}
