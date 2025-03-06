public class Address{
	private String city;
	private String street;

	Address(String city, String street){
		this.city = city;
		this.street = street;
	}

	Address(Address other){
		this.city = other.city;
		this.street = other.street;
	}

	public void showAddress(){
		System.out.println("City: " + city);
		System.out.println("Street: "+ street);
	}

	public boolean equals(Address obj){
		if((this.city == obj.city) && (this.street == obj.street)){
			return true;
		}
		else{
			return false;
		}
	}

	public String getCity(){
		return city;
	}

	
	public String getStreet(){
		return street;
	}

	public void setCity(String city){
		this.city = city;
	}

	public void setStreet(String street){
		this.street = street;
	}

}