public class Student extends Person
{
	String rollNo;

	public Student(String name, int age, String rollNo)
	{
		super(name, age);
		this.rollNo = rollNo;
	}

	@Override
	public String toString()
	{
		return String.format("Name: %s, Age: %d, Subject: %s, Roll Number: %s", name, age, rollNo);
	}

	public boolean equals(Student obj)
	{
		Student student = (Student) obj;
 		if(rollNo.equals(student.rollNo))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
} 