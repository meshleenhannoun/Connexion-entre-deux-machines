import java.io.IOException;


public class RTest {

	public static void main(String[] args) throws IOException {
	
		int Numero_port=Integer.parseInt(args[0]);
		Recepteur application =new Recepteur(Numero_port);
		application.runRecepteur();
		

	}

}
