package fr.astro.entity.field;

import java.util.Set;

/**
 * Field
 * 
 * Represents a field for a class
 */
public abstract class Field {

    protected static Set<String> elements;
    protected String name;

    /**
     * Defines the elements
     * 
     */
    public Field(String name) {
        this.name = name;
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
     * @param name
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

    /**
     * Return a field
     * 
     * @param name
     * @return a field
     */
    public static Field of(String name) {
        return null;
    }

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