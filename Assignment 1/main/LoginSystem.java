import users.UserManager;
import java.io.console  

public class Main{
	public static void main(String[] args){
		java.io.Console console = System.console();

		String username;
		char[] password;
		String pw;

		Scanner in = new Scanner(System.in);
		System.out.println("Enter Username: ");
		username = in.next();
		password = console.readPassword("Enter Password: ");
		pw = String.valueOf(password);
		
		boolean isAuthenticated  = UserManager.LoginProcessor.authenticate(username, password);
		
		if(isAuthenticated){
			System.out.println("Access granted");
		}else{
			System.out.println("Access DENIED");
		}
	}
}