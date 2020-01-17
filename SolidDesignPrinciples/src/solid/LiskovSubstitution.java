package solid;

public class LiskovSubstitution {

	public static void main(String[] args) {
		Rectangle r = new Rectangle(2,3);
		useIt(r);
		
		Rectangle sq = new Square();
		sq.setWidth(5);
		sq.setHeight(5);
		useIt(sq);
		
		
		//New Code
		Rectangle newSq = RectangleFactory.newSquare(5);
		System.out.println("Area : " + newSq.getArea());
		
		Rectangle newRect = RectangleFactory.newRectangle(5, 10);
		System.out.println("Area : " + newRect.getArea());
		
	}
	
	public static void useIt(Rectangle r) {
		int width = r.getWidth();
		r.setHeight(10);
		
		System.out.println("Expected Area : " + width*10 + " and Actual Area : " + r.getArea());
	}
}

class Rectangle {
	int width;
	int height;

	public Rectangle() {}

	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getArea() {
		return width * height;
	}
	
	//New Code
	public boolean isSquare() {
		return width == height;
	}
	
	@Override
	public String toString() {
		return "Rectangle [width=" + width + ", height=" + height + "]";
	}

}

class Square extends Rectangle {
	int size;
	
	public Square() {}
	
	public Square(int size) {
		width = height = size;
	}

	public void setWidth(int size) {
		super.setWidth(size);
		super.setHeight(size);
	}
	
	public void setHeight(int size) {
		super.setWidth(size);
		super.setHeight(size);
	}
}


//New Code 
class RectangleFactory {
	public static Rectangle newSquare(int size) {
		return new Rectangle(size,size);
	}
	
	public static Rectangle newRectangle(int width, int height) {
		return new Rectangle(width,height);
	}
}