package fr.astro.dao;

import java.sql.SQLException;
import java.util.Set;

/**
 * SQLObjectAndSet
 * 
 * Represents an object that can be saved in a database
 * 
 * T -> Main object
 * E -> Objects contained in the main object
 */
public interface SQLObjectAndRelatedSet<T, E> extends SQLObject<T> {

    /**
     * Return a set of objects contained in the main object
     * 
     * @param object
     * @return a set of objects contained in the main object
     */
    public Set<E> getRelatedSet(T object) throws SQLException;

}
