package com.org.functional_interface_comparators_filter;

public class Person {
	private final String name;
	private final int age;

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
	
	public int ageDifference(final Person other){
		return age - other.age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
}
