package com.wiquery.plugins.service;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wiquery.plugins.PersonQueries;
import com.wiquery.plugins.jdo.JdoQueries;
import com.wiquery.plugins.model.Person;

/**
 * This class provides an implementation of GreetingQueries using JDO Query objects.
 */
public class JdoPersonQueries extends JdoQueries<Person> implements PersonQueries
{
    @Inject
    public JdoPersonQueries(Provider<PersistenceManager> pmProvider)
    {
        super(Person.class, pmProvider);
    }

    @Override
    public List<Person> latest(int max)
    {
        Query query = newQuery();
        query.setOrdering("date desc");
        query.setRange(0, max);
        return toList(query.execute());
    }
}
