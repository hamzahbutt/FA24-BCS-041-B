public class Teacher extends Person
{
	String subject;
	String ID;

	public Teacher(String name, int age, String subject, String ID)
	{
		super(name, age);
		this.subject = subject;
		this.ID = ID;
	}

	@Override
	public String toString()
	{
		return String.format("Name: %s, Age: %d, Subject: %s, ID: %s", name, age, subject, ID);
	}

	public boolean equals(Teacher obj)
	{
		Teacher teacher = (Teacher) obj;
 		if(ID.equals(teacher.ID))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
} 