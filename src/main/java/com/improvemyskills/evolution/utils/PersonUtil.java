package com.improvemyskills.evolution.utils;


import com.improvemyskills.evolution.models.Address;
import com.improvemyskills.evolution.models.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonUtil {

    public static Person buildIncompletePerson() {
        Person p = new Person(1L,"jean","Bon");
        return p;
    }

    public static Person buildCompletePerson() {
        Person p = new Person(2L,"alex","Therieur");
        p.setEmail("alex.therieur@xyz.com");
        p.setAddress(new Address("12", "rue Elle" , "75000" , "Paris"));
        return p;
    }

    public static Person buildCompletePersonWithIncompleteBestFriend() {
        Person p = new Person(3L,"axelle","Aire");
        p.setEmail("axelle.Aire@abc.com");
        p.setAddress(new Address("16", "rue abc" , "69000" , "Lyon"));
        p.setBestFriend(buildIncompletePerson());
        return p;
    }

    public static Person buildCompletePersonWithCompleteBestFriend() {
        Person p = new Person(4L,"alain","Therieur");
        p.setEmail("alain.therieur@abc.com");
        p.setAddress(new Address("8", "rue xyz" , "80000" , "Amiens"));
        p.setBestFriend(buildCompletePerson());
        return p;
    }

    public static List<Person> buildSamplePersonList(){
        List<Person> pList = new ArrayList<>();
        pList.add(buildIncompletePerson());
        pList.add(buildCompletePerson());
        pList.add(buildCompletePersonWithIncompleteBestFriend());
        pList.add(buildCompletePersonWithCompleteBestFriend());
        return pList;
    }
}
