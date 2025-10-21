package com.improvemyskills.evolution.models;


import java.util.Optional;

public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private Address address; //may be null (optional)
    private Person bestFriend; //may be null (optional)
    private String email; //may be null (optional)

    private int age;
    //...

    public Person() {
        super();
    }


    public Person(Long id, String firstName, String lastName) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }



    @Override
    public String toString() {
        return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Address getAddress() {
        return address;
    }

    public Optional<Address> fetchOptionalAddress() {
        return Optional.ofNullable(address);
    }


    public void setAddress(Address address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }

    public Optional<String> fetchOptionalEmail() {
        return Optional.ofNullable(email);
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public Person getBestFriend() {
        return bestFriend;
    }

    public Optional<Person> fetchOptionalBestFriend() {
        return Optional.ofNullable(bestFriend);
    }


    public void setBestFriend(Person bestFriend) {
        this.bestFriend = bestFriend;
    }



}

