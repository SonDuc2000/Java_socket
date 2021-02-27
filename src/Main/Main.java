package Main;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Đây là client");
		Client client = new Client();
		client.startConnection("127.0.0.1", 8080);
	}

}
