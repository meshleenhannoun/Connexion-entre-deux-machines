import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LireFichier {
	
	
	/*public static void main(String args[]) throws IOException {
		Donner(args[0]);
		
	}*/
	public String Donner(String fichier) throws IOException {
		
	    BufferedReader lecteurAvecBuffer = null;
	    String ligne;
	    String ligne2="";
	    String donner="";

	    try
	      {
		lecteurAvecBuffer = new BufferedReader(new FileReader(fichier));
	      }
	    catch(FileNotFoundException exc)
	      {
		System.out.println("Erreur d'ouverture");
	      }
	    while ((ligne = lecteurAvecBuffer.readLine()) != null) {
	    	ligne2=ligne2+ligne;
	      //System.out.println(ligne);
	    	}
	    lecteurAvecBuffer.close();
	   // System.out.println(ligne2);
	    byte[] bytes = ligne2.getBytes();
	    for (byte b : bytes) {
	        donner=donner+Integer.toBinaryString(b & 255 | 256).substring(1);
	        //System.out.println(donner);
	  }
	
		
	
		return donner;
	}
	
	
	public String convertir(int numero) {
		
		  byte  Num=(byte)numero;
	       String chaine = String.format("%8s", Integer.toBinaryString(Num & 0xFF)).replace(' ', '0');
	       //System.out.println(a1); // 10000001
	       return chaine;
	       
	}
}