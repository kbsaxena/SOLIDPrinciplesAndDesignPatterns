package solid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.javatuples.Triplet;

/**
 * 
 * @author Kulbhushan_Saxena
 *
 */

/*
 * 1)High level modules should not be dependent on low level modules
 * 2)Use abstarction
 *
 */
public class DependencyInversion {

	public DependencyInversion() {
	}

	public static void main(String[] args) {
		Relationships relationships = new Relationships();
		
		Person parent = new Person("John");
		Person child1 = new Person("Johnson");
		Person child2 = new Person("Johnson And Johnson");
		
		relationships.addParentAndChild(parent, child1);
		relationships.addParentAndChild(parent, child2);
		
		Research research = new Research(relationships);
	}

}

enum Relationship {
	PARENT, CHILD, SIBLING
}

class Person {
	String name;

	public Person(String name) {
		super();
		this.name = name;
	}
}

interface RelationshipBrowser {
	List<Person> getAllChilds(String name);
}

class Relationships implements RelationshipBrowser{ //low level as it contains only data
	List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

	public List<Triplet<Person, Relationship, Person>> getRelations() {
		return relations;
	}

	public void addParentAndChild(Person parent, Person child) {
		relations.add(new Triplet<Person, Relationship, Person>(parent, Relationship.PARENT, child));
		relations.add(new Triplet<Person, Relationship, Person>(child, Relationship.CHILD, parent));
	}

	@Override
	public List<Person> getAllChilds(String name) {
		return relations.stream()
				.filter(t -> Objects.equals(t.getValue0().name, name) 
						&& t.getValue1() == Relationship.PARENT)
				.map(Triplet::getValue2)
		        .collect(Collectors.toList());
	}
}

class Research { // high level as it perform some operations on data
    //Old Code
	/*public Research(Relationships relationships) {
		List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
		relations.stream().filter(t -> t.getValue0().name.equals("John") && t.getValue1() == Relationship.PARENT)
				.forEach(t -> System.out.println("Child of John is :" + t.getValue2().name));

	}*/
	
	//New Code
	public Research(RelationshipBrowser relationshipBrowser) {
		List<Person> list = relationshipBrowser.getAllChilds("John");
		list.forEach(p->System.out.println("Johns child : " + p.name));
	}

}
