class Product{
	static int idCounter = 1;
	private String productid;												
	private String productName;
	private int productQuantity;
	private double productPrice;
	private String productCategory;

	public Product(String prodN, String prodC, int prodQ, double prodP){
	productid = "" + idCounter++;
	productName = prodN;
	productQuantity = prodQ;
	productPrice = prodP;
	productCategory = prodC;

	}
	
	public String GetProductId(){
		return productid;
	}
	
	public double GetProductPrice(){
		return productPrice;
	}	

	public int GetProductQuantity(){
		return productQuantity;
	}

	public String GetProductCategory(){
		return productCategory;
	}
	
	public String GetProductName(){
		return productName;
	}
	



	public void SetProductName(String pN){
		productName = pN;
	}
	
	public void SetCategory(String pC){
		productCategory = pC;
	}

	public void SetProductPrice(Double pP){
		productPrice = pP;
	}	

	public void SetProductQuantity(int pQ){
		productQuantity = pQ;
	}

	public void displayProduct(){
		System.out.println("Product ID: " + productid);
		System.out.println("Product Name: " + productName);
 		System.out.println("Product Category: " + productCategory);
		System.out.println("Product Quantity: " + productQuantity);		
		System.out.println("Product Price: " + productPrice);
		System.out.println("------------------------------------------");


	}

	public static void main(String[] args){
		Product p1 = new Product("Smartphone", "Electronics", 10, 499.99);
		Product p2 = new Product("T-Shirt", "Clothings", 25, 16.99);
		Product p3 = new Product("The Alchemist", "Book", 12, 10.99);	
		Product p4 = new Product("Chair", "Furniture", 10, 75.99);
		
		p1.displayProduct();
		p2.displayProduct();
		p3.displayProduct();
		p4.displayProduct();
	

	}

	

}