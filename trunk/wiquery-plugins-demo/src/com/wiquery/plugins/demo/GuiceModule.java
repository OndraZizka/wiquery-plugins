package com.wiquery.plugins.demo;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.wiquery.plugins.PersonQueries;
import com.wiquery.plugins.Repository;
import com.wiquery.plugins.jdo.PersistenceManagerFilter;
import com.wiquery.plugins.model.Person;
import com.wiquery.plugins.service.JdoPersonQueries;
import com.wiquery.plugins.service.JdoPersonRepository;

/**
 * This Guice module sets up the bindings used in this Wicket application, including the
 * JDO PersistenceManager.
 */
public class GuiceModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        // Enable per-request-thread PersistenceManager injection.
        install(new PersistenceManagerFilter.GuiceModule());

        // Business object bindings go here.
        bind(PersonQueries.class).to(JdoPersonQueries.class);
        bind(new TypeLiteral<Repository<Person>>() { }).to(JdoPersonRepository.class);
    }
}
