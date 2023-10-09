package fr.astro.entity.field;

import java.util.Set;

/**
 * Field
 * <p>
 * Represents a field for a class
 */
public abstract class Field {

    protected static Set<String> elements;
    protected String name;

    /**
     * Defines the elements
     *
     * @param name the name of the element
     */
    public Field(String name) {
        this.name = name;
    }

    /**
     * Return a field
     *
     * @param name the name of the field
     * @return a field
     */
    public static Field of(String name) {
        return null;
    }

    /**
     * Return the name of the element
     *
     * @return the name of the element
     */
    public String getName() {
        return name;
    }

    /**
     * Return true if the name is a valid element
     *
     * @param name the name to check
     * @return true if the name is a valid element
     */
    public boolean isElement(String name) {

        if (elements == null) {
            return false;
        }

        return elements.contains(name.toLowerCase());
    }

    /**
     * Return the name of the field
     *
     * @return the name of the field
     */
    public abstract String getFieldName();

    @Override
    public String toString() {
        return getFieldName() + " : " + name;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Field)) {
            return false;
        }

        Field field = (Field) obj;

        return field.getName().equals(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}