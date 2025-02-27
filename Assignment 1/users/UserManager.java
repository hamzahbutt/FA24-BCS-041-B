package users;
public class UserManager{
	static final String StoredUsername = "Hamzah";
	static final String storedPassword= "12345";

	public static class LoginProcessor{
		public static boolean authenticate(String username, String password){
			if(storedUsername.equals(username)){
				if(storedPassword.equals(password)){
					return true;
				}
			}
			else{
				return false;
			}
		}

	}	

}