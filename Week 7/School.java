public class School 
{
	private String name;
    	private String address;
    	private Principal principal;
    	private Classroom[] classes;
    	private int classCount = 0;

    	public School(String name, String address, Principal principal) 
	{
        	this.name = name;
        	this.address = address;
        	this.principal = principal;
        	this.classes = new Classroom[10];
    	}

    	public void addClassroom(Classroom classroom) 
	{
        	if (classCount >= 10) 
		{
            	System.out.println("Maximum Classes Reached.");
        	} 
		else 
		{
            	classes[classCount++] = classroom;
        	}
    	}

}