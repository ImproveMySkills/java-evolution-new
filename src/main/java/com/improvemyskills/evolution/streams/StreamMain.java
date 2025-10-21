package com.improvemyskills.evolution.streams;

import com.improvemyskills.evolution.models.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamMain {


    private static final String IT = "IT";
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Ali", "IT", 3000),
                new Employee("Bobo", "HR", 2500),
                new Employee("Cissé", "IT", 4000),
                new Employee("Anna", "Finance", 3500)
        );


        List<Employee> evenIndexEmployees = IntStream.range(0, employees.size())
                .filter(i -> i % 2 == 0) // garde les index pairs
                .mapToObj(employees::get) // récupère l'élément à cet index
                .toList();

        evenIndexEmployees.forEach(System.out::println);


        List<Employee> evenIndexEmployees2 =
                Stream.iterate(0, i -> i + 2) // commence à 0, incrémente de 2
                        .limit((employees.size() + 1) / 2) // limite au nombre d'éléments pairs
                        .map(index -> employees.get(index)) // récupère l'élément à cet index
                        .toList();

        evenIndexEmployees2.forEach(System.out::println);




        // 1. Filtrer les employés du département IT
        List<Employee> itEmployees = employees.stream()
                .filter(e -> IT.equals(e.getDepartment()))
                .collect(Collectors.toList());

        System.out.println("Employés IT :");
        itEmployees.forEach(System.out::println);

        // 2. Augmenter les salaires de 10%


        List<Employee> updatedSalaries = employees.stream()
                .map(e -> new Employee(e.getName(), e.getDepartment(), e.getSalary() * 1.1))
                .collect(Collectors.toList());

        var updatedVarSalaries = employees.stream()
                .map(e -> new Employee(e.getName(), e.getDepartment(), e.getSalary() * 1.1))
                .collect(Collectors.toList());


        List<Double> salaries = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.toList());

        System.out.println("\nSalaires augmentés :");
        updatedSalaries.forEach(System.out::println);

        // 3. Calculer la somme des salaires
        double totalSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();

        double salary = employees.stream()
                .map(employee -> employee.getSalary())
                        .reduce(0D, (aDouble, aDouble2) -> aDouble + aDouble2);

        System.out.println("\nSalaire total : " + totalSalary);
        System.out.println("\nSalaire total : " + salary);
    }
}
