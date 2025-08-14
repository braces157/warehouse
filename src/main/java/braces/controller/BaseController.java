/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package braces.controller;

import java.util.List;
import java.util.Optional;

/**
 * Base controller interface providing common CRUD operations and search
 * functionality
 *
 * @param <T> Entity type
 * @param <ID> ID type
 * @author Braces
 */
public interface BaseController<T, ID> {

    List<T> getAll();

    Optional<T> getById(ID id);

    T save(T entity);

    void deleteById(ID id);

    List<T> getSearchTable(String text, String searchType);
}
