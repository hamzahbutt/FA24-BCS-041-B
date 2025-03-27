public class Principal extends Person
{
	int experience;

	public Principal(String name, int age, int experience)
	{
		super(name, age);
		this.experience = experience;
	}

	@Override
	public String toString()
	{
		return String.format("Name: %s, Age: %d, Experience: %d" , name, age, experience);
	}
} 