package pl.edu.agh.ki.bd2.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer dbID;

    private String name;

    @Embedded
    private Address address = new Address();

    public Company() {
    }

    public Company(String name, String city, String street, String zipcode) {
        address.setCity(city);
        address.setStreet(street);
        address.setZipcode(zipcode);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public Integer getDbID() {
        return dbID;
    }
}
