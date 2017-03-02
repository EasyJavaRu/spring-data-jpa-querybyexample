package ru.easyjava.spring.data.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Collection;

@SuppressWarnings("PMD")
@Entity
public class Address extends AbstractIdentifiableObject {
    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String street;

    @Getter
    @Setter
    private String building;

    @Getter
    @Setter
    @OneToMany(mappedBy = "primaryAddress", fetch = FetchType.EAGER)
    private Collection<Person> tenants;

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + "\'}";
    }
}
