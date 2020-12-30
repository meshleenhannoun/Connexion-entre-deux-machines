import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.SwingUtilities;

public class Recepteur {
	private String verifierTrames;// type Classe  a venir.
	private int Numero_Port;
	private ObjectOutputStream output;//sortie flux de donner pour client
	private ObjectInputStream input; //entreé flux de donner du client
	private ServerSocket recepteur; //server socket
	private Socket connection; //connection du client
	private int compter=1;// compter le nombre de connection du client.
	private Trames tram;
	private bitStuffing bitS;
	private AfficherTrame affiche;
	private TestErreur erreur;
	
	public Recepteur(int Port) {
		this.Numero_Port=Port;
		
	}
	
	
	public void runRecepteur() {
		try {
			recepteur=new ServerSocket(Numero_Port);
			
			while(true) {
				try {
					attendreConnection();
					getStream();
					processConnection();
					
				}
			
	
             catch(EOFException eofException) {
	         System.out.println("\nServer terminated connection");
           }
           finally {
	                 closeConnection();
	                 ++compter;
}
			}
		}
   catch(IOException ioException) {
			ioException.printStackTrace();
	}
}

	
	private void attendreConnection() throws IOException {
		System.out.println("attendre la connection");
		connection=recepteur.accept();
		System.out.println("\nNom de la machine " +connection.getInetAddress().getHostAddress());

	}
	
	
	private void getStream() throws IOException{
		output= new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		
		input= new ObjectInputStream(connection.getInputStream());
		

}
	

   private void processConnection()throws IOException{
      affiche=new AfficherTrame();
      
      bitS=new bitStuffing();
      String type="";
      String message="";
      
      //do {
    	  try {
    		 Trames [] tra=(Trames[])input.readObject();
    		 
    		 for(int i=0; i<tra.length; i++) {
    			 Trames tram1=tra[i];
    		     String donne=tra[i].donne;
    		     String desBitStu="";
    		     String flag=tram1.flag;
    		     type=tram1.type;
    		     String Num=tram1.Num;
    		     String CRC=tram1.CRC;
    		     String Donnees=tram1.Donnees;
    		     String to_String=tram1.to_String1();
    		     String chaineVerifier=type+Num+Donnees+CRC;
    		     String chaine="";
    		 
    		     erreur=new TestErreur(CRC,type,donne);
    		    if(!erreur.TrameErronée(donne, CRC, message)&&erreur.Type(type)) {
    		    	erreur.Type(type);
    		       	///message="Trame recu merci.";
    		    	output.writeObject(erreur.getChainee());
    		    	output.flush();
    		    	
    		    	affiche.afficheString(flag, type, Num, CRC, Donnees, donne,to_String);
    			    Trames trams=new Trames(flag,type,Num,Donnees,CRC,donne);
    			    //Trames traa=new Trames(flag,donne,trams);
    			   trams.setType("01000001");
    			  
  		            sendAccuserR(tra[i]);
    		  }
    	    	else {
    		         message="Trame erreur";
    			     output.writeObject(message);  
    			     output.flush();
    		}
    			
    		 
    		  
    		 }
    	  }
    	  
    	  catch(ClassNotFoundException classNotFoundException) {
    		  System.out.println("\nUnknown object type received");
    	  }
      catch(SocketException e) {
    	  System.out.println("Erreur de connection "+e);
      }
      //} while(type.equals("01000110"));//(!message.equals("Recepteur>>> TERMINATE"));	
      
   }
   
   public void closeConnection() {
	   System.out.println("\nTerminating connection\n");
	   
	   
	   try {
		   //output.close();
		   input.close();
		   connection.close();
	   }
	   catch(IOException ioException) {
		   ioException.printStackTrace();
	   }
   }
   
   
   private void sendAccuserR(Trames accuR) {
	   try {
		   output.writeObject(accuR);
		   output.flush();
		   //System.out.println("Accusé de reception " +tram.to_String());
	   }
	   catch(IOException ioException) {
		   System.out.println("\nError writing object");
	   }
   }
	   
	   
}