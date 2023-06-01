package src.fr.astro.dao;

import java.sql.SQLException;

import src.fr.astro.exception.sql.ObjectNotFound;

public interface SQLObject<T> {

    /**
     * Save the object in the database
     * 
     * @return true if the object is saved, false otherwise
     * @throws ObjectNotFound
     */
    public boolean save(T object) throws ObjectNotFound, SQLException;

    /**
     * Update the object in the database
     * 
     * @return true if the object is updated, false otherwise
     */
    public boolean update(T object) throws ObjectNotFound, SQLException;

    /**
     * Delete the object in the database
     * 
     * @return true if the object is deleted, false otherwise
     */
    public boolean delete(T object) throws ObjectNotFound, SQLException;

    /**
     * Return the object in the database
     * 
     * @return the object in the database
     */
    public T get(int id) throws ObjectNotFound, SQLException;

    /**
     * Return true if the object exist in the database, false otherwise
     * 
     * @return true if the object exist in the database, false otherwise
     */
    public boolean exist(int id) throws SQLException;

    /**
     * Return true if the object exist in the database, false otherwise
     * 
     * @return true if the object exist in the database, false otherwise
     */
    public boolean exist(T object) throws SQLException;

}
