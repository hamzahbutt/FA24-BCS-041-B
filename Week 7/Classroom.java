public class Classroom
{
	private String name;
	private String code;
	private Teacher teacher;
	private Student students[];
	private int studentCount = 0;

	public Classroom(String name, String code, Teacher teacher)
	{
		this.name = name;
		this.code = code;
		this.teacher = teacher;
		this.students = new Student[5];
	}

    	public void addStudent(Student student) 
	{
		if (studentCount >= 5) 
		{
            		System.out.println("Cannot add student. Class is full.");
        	} 
		else 
		{
            		students[studentCount++] = student;
            		System.out.println("Student " + student.name + " added to " + name);
        	}

	}
}