package com.wiquery.plugins;

import java.util.List;

import com.wiquery.plugins.model.Person;

/**
 * This interface defines type-safe methods for all queries of Greeting entities.
 */
public interface PersonQueries
{
    /**
     * Return the latest greetings, ordered by descending date.
     *
     * @param max the maximum number of greetings to return
     * @return the greetings
     */
    List<Person> latest(int max);
}
