public class Screen
{
	int screenNumber;
	String movieTitle;
	Seat seats[][];

	Screen(int screenNumber , String movieTitle, int row, int cols)
	{
		this.screenNumber = screenNumber;
		this.movieTitle = movieTitle;
		this.seats = new Seat[row][cols];
	}

	public boolean bookSeat(int row, int cols)
	{
		return seats[row][cols].bookSeat();	
	}

	public void displayScreen()
	{
		System.out.println("Screen Details: ");
		System.out.println("Screen Number: " + screenNumber);
		System.out.println("Movie Title: " +  movieTitle);
	}
}