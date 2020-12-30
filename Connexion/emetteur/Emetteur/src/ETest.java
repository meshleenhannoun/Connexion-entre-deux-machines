import java.io.IOException;

public class ETest {

	public static void main(String[] args) throws InterruptedException {
	     Emetteur application;
	     
	     String host=args[0];
	     int port=Integer.parseInt(args[1]);
	     String fichier=args[2];
	     int goBackN=Integer.parseInt(args[3]);
	 

	    	 application=new Emetteur(host,port,fichier,goBackN);
	     application.runEmetteur();
	}

}
