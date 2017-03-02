package ru.easyjava.spring.data.jpa.entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.easyjava.spring.data.jpa.dal.PassportRepository;
import ru.easyjava.spring.data.jpa.dal.PersonRepository;
import static org.junit.Assert.assertFalse;

@ContextConfiguration(classes = ru.easyjava.spring.data.jpa.ContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DalIT {
    @Inject
    private PersonRepository personRepository;

    @Inject
    private PassportRepository passportRepository;

    @Before
    @Transactional
    public void setUp() throws Exception {
        Passport p = new Passport();
        p.setSeries("AS");
        p.setNo("123456");
        p.setIssueDate(LocalDate.now());
        p.setValidity(Period.ofYears(20));

        Address a = new Address();
        a.setCity("Kickapoo");
        a.setStreet("Main street");
        a.setBuilding("1");

        Person person = new Person();
        person.setFirstName("Test");
        person.setLastName("Testoff");
        person.setDob(LocalDate.now());
        person.setPrimaryAddress(a);
        person.setPassport(p);

        Company c = new Company();
        c.setName("Acme Ltd");

        p.setOwner(person);
        person.setWorkingPlaces(Collections.singletonList(c));

        personRepository.save(person);
    }

    @Test
    @Transactional
    public void testGreeter() {
        Person queryPerson = new Person();
        queryPerson.setFirstName("Test");

        Example<Person> exampleQuery = Example.of(queryPerson);

        personRepository.findAll(exampleQuery)
                .forEach(System.out::println);

        ExampleMatcher firstNameMatcher = ExampleMatcher.matching()
                .withMatcher("firstname", m -> m.startsWith());
        queryPerson.setFirstName("fail");
        Example<Person> failingQuery = Example.of(queryPerson, firstNameMatcher);
        assertFalse(personRepository.exists(failingQuery));
    }
}
