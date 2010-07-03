package com.wiquery.plugins.service;

import javax.jdo.PersistenceManager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wiquery.plugins.jdo.JdoRepository;
import com.wiquery.plugins.model.Person;

public class JdoPersonRepository extends JdoRepository<Person>
{
    @Inject
    public JdoPersonRepository(Provider<PersistenceManager> pmProvider)
    {
        super(Person.class, pmProvider);
    }
}