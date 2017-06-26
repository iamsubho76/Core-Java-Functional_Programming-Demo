package com.org.functional_interface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class Test_Collections_And_Lambda_Expressions {
	public static void main(String[] args) {
		List<Integer> listOfInteger = Arrays.asList(1,2,3,4,5,6,7,8,9);
		
		listOfInteger.forEach(new Consumer<Integer>() {
			public void accept(Integer value) {
				System.out.println(value);
			};
		});
		
		listOfInteger.forEach((Integer i) -> System.out.println(i));
		
		listOfInteger.forEach(i -> System.out.println(i));
		
		listOfInteger.forEach(System.out::println);
		
		//Transforming a List
		final List<String> pack = Arrays.asList("a","ab","abc","pabcd","qabcde");
		final List<String> packUpper = new ArrayList<String>(); 
		pack.forEach(name -> packUpper.add(name.toUpperCase()));
		System.out.println(packUpper);
		
		pack.stream().map(name -> name.toUpperCase(Locale.ROOT)).forEach(System.out::println);
		
		pack.stream().map(String::toUpperCase).forEach(System.out::println);
		
		//Finding Element
		pack.stream().filter(n -> n.startsWith("a")).collect(Collectors.toList());
		
		
		final Predicate<String> filterName = name -> name.startsWith("a");
		pack.stream().filter(filterName).collect(Collectors.toList());
		
		pack.stream().filter(criteria("a")).collect(Collectors.toList());


		
		final Function<String, Predicate<String>> predicateFunction = (String letter)->{
			Predicate<String> criteria = (String name) -> name.startsWith(letter);
			return criteria;
		};
		pack.stream().filter(predicateFunction.apply("a	")).collect(Collectors.toList());
		
		//L-13 multiple lambda expressions
		final Function<String, Predicate<String>> predicateFunctionInOneLine = 
				(String letter)-> (String name) -> name.startsWith(letter);
	
		final long number = pack.stream().filter(predicateFunctionInOneLine.apply("a")).count();
		System.out.println(number);
		//ends
		
		//Optional Interface
		pickName(pack, "a");
		
		//Reducing Operation
		//reduce will always return you one result
		pack.stream().mapToInt(name->name.length()).sum();
		pack.stream().mapToInt(name->name.length()).max();
		pack.stream().mapToInt(name->name.length()).average();
		
		
		Optional<String> longestName = pack.stream().reduce((name1, name2)->name1.length()>=name2.length() ? name1 : name2);
		System.out.println("The longest name is : " + longestName);
		
		//Joining Element
		System.out.println(String.join(",", pack));
		System.out.println(pack.stream().filter(n -> n.startsWith("a")).collect(Collectors.joining(", ")));
	}
	
	//lexical scoping
	public static Predicate<String> criteria(final String letter){
		return name -> name.startsWith(letter);
	}
	
	//L-15 Picking One Element
	public static void pickName(final List<String> listOfName, String startingLetter){
		final Optional<String> foundName = 
				listOfName.stream().filter(name->name.startsWith(startingLetter)).findFirst();
		System.out.println("The first name that begins with : " + startingLetter + " : " + foundName.orElse(" no name was found."));
		foundName.ifPresent(name->System.out.println(name));
	}
	//end
}
