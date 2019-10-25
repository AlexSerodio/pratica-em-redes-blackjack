import java.util.List;

public class Main {
	
	private static String userAlex = "9534";
	private static String passwordAlex = "jqcal";
	
	private static String userBarbara = "6464";
	private static String passwordBarbara = "cbxep";
	
	private static List<User> users;
	private static RequestService rs = new RequestService(userAlex, passwordAlex);
	
	
	public static void main(String args[]) {
	
		// recupera a lista de usuários a cada 6 segundos, como pedido no enunciado
		getUsersThread.start();
		
		rs.sendMessage("Uma mensagem de teste", userBarbara);
		
		RequestService rs2 = new RequestService(userBarbara, passwordBarbara);
		
		Message message = rs2.getMessage();
		System.out.println("Message: " + message);
	}
	
	private static Thread getUsersThread = new Thread() {
		@Override
	    public void run() {
			while(true) {
				
				users = rs.getUsers();
				System.out.println("Users: " + users);
				
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
}
