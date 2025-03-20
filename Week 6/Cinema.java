public class Cinema
{
	String name; 
	String location;
	Screen[] screens;

	Cinema(String name, String location, int totalScreens)
	{
		this.name = name;
		this.location = location;
		this.screens = new Screen[totalScreens];
	}	

	public void addScreen(int index, Screen screen)
	{
		this.screens[index] = screen;
	}
	
	public void displayCinema()
	{
		System.out.println("Cinema Details: ");
		System.out.println("Cinema Name: " + name);
		System.out.println("Cinema Location: " + location);
	}

}