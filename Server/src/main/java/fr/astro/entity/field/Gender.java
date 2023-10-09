package fr.astro.entity.field;

import java.util.Set;

public class Gender extends Field {

    private static final Set<String> elementsSet = Set.of(
            "m",
            "f");

    public Gender(String name) {
        super(name);

        elements = elementsSet;

        if (!isElement(name)) {
            throw new IllegalArgumentException("The name is not a valid element");
        }

    }

    public static Gender of(String name) {
        return new Gender(name);
    }

    public static String randomElement() {

        int random = (int) (Math.random() * (elementsSet.size() - 1));

        int i = 0;

        for (String genderName : elementsSet) {

            if (i == random) {
                return genderName;
            }

            i++;

        }

        return null;
    }

    @Override
    public String getFieldName() {
        return "Gender";
    }

}
