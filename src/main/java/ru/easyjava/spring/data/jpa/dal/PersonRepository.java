package ru.easyjava.spring.data.jpa.dal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import ru.easyjava.spring.data.jpa.entity.Person;

/**
 * Repository for person entity.
 */
public interface PersonRepository extends CrudRepository<Person, Long>, QueryByExampleExecutor<Person> { }
