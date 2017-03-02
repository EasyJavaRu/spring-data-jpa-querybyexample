package ru.easyjava.spring.data.jpa.dal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import ru.easyjava.spring.data.jpa.entity.Passport;

/**
 * Repository for passport entity.
 */
public interface PassportRepository extends CrudRepository<Passport, Long>, QueryByExampleExecutor<Passport> { }
