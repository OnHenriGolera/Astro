package fr.astro.entity.field;

public abstract class OptionalField extends Field {

    public OptionalField(String name) {
        super(name);
    }

    protected abstract void setDefaultElement();
    
}
