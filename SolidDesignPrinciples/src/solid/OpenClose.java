package solid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

enum Color {
	GREEN, RED, ORANGE
}

enum Size {
	SMALL, MEDIUM, LARGE
}

class Product {
	String name;
	Size size;
	Color color;

	public Product(String name, Size size, Color color) {
		this.name = name;
		this.size = size;
		this.color = color;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", size=" + size + ", color=" + color + "]";
	}
	
	
}

class ProductFilter {

	// Filter by color
	public static Stream<Product> getProductByColor(List<Product> list, Color color) {
		return list.stream().filter(p -> p.color == color);
	}

	// Filter by Size
	public static Stream<Product> getProductBySize(List<Product> list, Size size) {
		return list.stream().filter(p -> p.size == size);
	}

	// Filter by color and size
	public static Stream<Product> getProductByColorAndSize(List<Product> list, Color color, Size size) {
		return list.stream().filter(p -> p.color == color && p.size == size);
	}

	// Many methods with all combinations.....Its too time consuming
}


//New Approach

interface Specification<T>{
	boolean isSatisfy(T item);
}

interface Filter<T>{
	Stream<T> filteredProduct(List<T> items, Specification<T> s);
}

class ColorSpecification implements Specification<Product>{
	Color color;
	
	public ColorSpecification(Color color) {
		super();
		this.color = color;
	}

	@Override
	public boolean isSatisfy(Product item) {
		return item.color == color;
	}
}

class SizeSpecification implements Specification<Product>{
	Size size;
	
	public SizeSpecification(Size size) {
		super();
		this.size = size;
	}

	@Override
	public boolean isSatisfy(Product item) {
		return item.size == size;
	}
}

class AndSpecification<T> implements Specification<T> {

	Specification<T> first, second;
	
	public AndSpecification(Specification<T> first, Specification<T> second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public boolean isSatisfy(T item) {
		return first.isSatisfy(item) && second.isSatisfy(item);
	}
	
}

class BetterFilter implements Filter<Product> {

	@Override
	public Stream<Product> filteredProduct(List<Product> items, Specification<Product> s) {
		return items.stream().filter(p -> s.isSatisfy(p));
	}
	
}

public class OpenClose {
	public static void main(String[] args) {
		//Old Way
		System.out.print("Green Products (old): ");
		ProductFilter.getProductByColor(getProducts(), Color.GREEN).forEach(System.out::println);
		System.out.print("Small Products (old): ");
		ProductFilter.getProductBySize(getProducts(), Size.SMALL).forEach(System.out::println);
		
		//New Way
		System.out.print("Green Products (new): ");
		new BetterFilter().filteredProduct(getProducts(), new ColorSpecification(Color.GREEN)).forEach(System.out::println);
		System.out.print("Small Products (new): ");
		new BetterFilter().filteredProduct(getProducts(), new SizeSpecification(Size.SMALL)).forEach(System.out::println);
		System.out.print("Medium and Orange Products (new): ");
		new BetterFilter().filteredProduct(getProducts(), new AndSpecification<Product>(new ColorSpecification(Color.ORANGE),new SizeSpecification(Size.MEDIUM))).forEach(System.out::println);
	}

	public static List<Product> getProducts() {
		List<Product> list = new ArrayList<>();
		list.add(new Product("Apple", Size.SMALL, Color.RED));
		list.add(new Product("Watermelon", Size.LARGE, Color.GREEN));
		list.add(new Product("MuskMelon", Size.MEDIUM, Color.ORANGE));
		
		return list;
	}
}
