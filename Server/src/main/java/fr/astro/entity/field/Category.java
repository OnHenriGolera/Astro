package fr.astro.entity.field;

import java.util.Set;

public class Category extends Field {

    private static final Set<String> elementsSet = Set.of(
            "m9",
            "m11",
            "m13",
            "m15",
            "m17",
            "m20",
            "m23",
            "senior",
            "veteran");

    public Category(String name) {
        super(name);

        elements = elementsSet; // TODO : optimize

        if (!isElement(name)) {
            throw new IllegalArgumentException("The name is not a valid element");
        }

    }

    public static Category of(String name) {
        return new Category(name);
    }

    public static String randomElement() {

        int random = (int) (Math.random() * (elementsSet.size() - 1));

        int i = 0;

        for (String categoryName : elementsSet) {

            if (i == random) {
                return categoryName;
            }

            i++;

        }

        return null;
    }

    @Override
    public String getFieldName() {
        return "Category";
    }

}
