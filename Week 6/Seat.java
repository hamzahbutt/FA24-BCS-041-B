public class Seat
{
	int seatNumber;
	int rowNumber;
	SeatType seatType;
	double price;
	boolean isBooked;	

	
	Seat(int seatNumber,int rowNumber, SeatType seatType, double price)
	{
		this.seatNumber = seatNumber; 
		this.rowNumber = rowNumber;
		this.seatType = seatType;
		this.price = price;
		this.isBooked = false;
	}
	

	public boolean bookSeat()
	{
		if(!isBooked)
		{
			isBooked = true;
			return true;
		}
		else
		{
			return false;
		}
	}


	public void display()
	{
		System.out.println("Seat Number: " + seatNumber);
		System.out.println("Row Number: " + rowNumber);
		System.out.println("Seat Type: " + seatType);
		System.out.println("Price: " + price);
		System.out.println("Seat Status: " + isBooked);
	}	
		
}