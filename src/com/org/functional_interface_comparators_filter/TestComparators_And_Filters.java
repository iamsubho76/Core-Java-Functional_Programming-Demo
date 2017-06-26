package com.org.functional_interface_comparators_filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import sun.security.krb5.internal.PAEncTSEnc;

public class TestComparators_And_Filters {
	public static void main(String[] args) {
		//L-19
		String name = "aSubhobroto";
		//character to IntStream
		name.chars().forEach(System.out::println);
		//IntStream to char
		name.chars().mapToObj(ch->Character.valueOf((char)ch)).forEach(System.out::println);
		//L-20 Sorting Element
		final List<Person> persons = Arrays.asList(
				new Person("Subho", 27),
				new Person("Subho1", 37),
				new Person("Subho12", 45),
				new Person("Subho123", 17),
				new Person("Subho1234", 07),
				new Person("Subho12345", 77),
				new Person("Subho123456", 50),
				new Person("Subho1234567", 50),
				new Person("Subho12345678", 50),
				new Person("Subho123456789", 50)
				
				);
		persons.stream().
			sorted((person1, person2) -> person1.ageDifference(person2)).
			collect(Collectors.toList()).stream().forEach(System.out::println);
		
		//L-20 Comparator
		Comparator<Person> comp = (person1, person2) -> Integer.valueOf(person1.getAge()).compareTo(Integer.valueOf(person2.getAge()));
		persons.stream().
			sorted(comp.reversed()).
			collect(Collectors.toList()).
			stream().
			forEach(System.out::println);
		
		//L-23 Multiple And Fluent Comparisons
		Function<Person, String> byName = person -> person.getName();
		
		persons.stream().
			sorted(Comparator.comparing(byName)).
			collect(Collectors.toList()).
			stream().
			forEach(System.out::println);
		
		Function<Person, Integer> byAge = (person) -> (Integer)person.getAge();
		persons.stream().
			sorted(Comparator.comparing(byName).thenComparing(byAge)).
			collect(Collectors.toList()).
			stream().
			forEach(System.out::println);
		
		//L-24 Using The Collect Method
		final List<Person> sorted = persons.stream().
			sorted(Comparator.comparing(byName).thenComparing(byAge)).
			collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
		
		System.err.println(sorted);
		
		//L-25 Grouping Elements
		//group by age
		final Map<Integer, List<Person>> peopleGoupByAge = persons.stream().sorted(Comparator.comparing(byName)).
				collect(Collectors.groupingBy(byAge));
		System.out.println(peopleGoupByAge);
		
		//L-26 Grouping And Mapping
		//grouping and mapping
		//1st step you are grouping by elements by age in the List
		//2nd Step we transform the List of Person to list of String
		final Map<Integer, List<String>> peopleGoupByAgeNMapping = persons.stream().
				collect(Collectors.groupingBy(byAge, Collectors.mapping(byName, Collectors.toList())));
		
		//L-27 Using the Comparator Class
		//name and age by charact	
		Map<Character, Optional<Person>> collect = persons.stream().
				collect(Collectors.groupingBy(person->((Person) person).getName().charAt(0), 
						Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(byAge)))));
		
		//L-28 Listing All files in a Directory
		
	} 
}
