package fr.astro.entity.field;

public abstract class OptionalField extends Field {

    /**
     * Defines the elements
     *
     * @param name the name of the element
     */
    public OptionalField(String name) {
        super(name);
    }

    protected abstract void setDefaultElement();

}
