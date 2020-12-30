
import java.io.*;
import java.net.*;

import javax.swing.SwingUtilities;

public class Emetteur  {
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message;
	private int Numero_port;
	private String Nom_Machine;
	private int goBack;
	private String fich;
	private Socket Emetteu;
	private LireFichier lire;
	private CalculCrc verif;
	private bitStuffing bitSt;


    public Emetteur(String host,int port,String fichier, int go_backN) {
    	this.Nom_Machine =host;
    	this.Numero_port=port;
    	this.fich=fichier;
    	this.goBack=go_backN;
    	
	}
	
	public void runEmetteur() throws InterruptedException {
		
				try {
					connecterRecepteur();
					getStream();
					EnvoyerLesTrames();
				
					
				}
				
             catch(EOFException eofException) {
	         System.out.println("\nFermeture de connection");
           }
			catch(IOException ioException) {
				ioException.printStackTrace();
			}
           finally {
	                 closeConnection();
	                 }
			}
	
	private void connecterRecepteur() throws IOException {
		System.out.println("Attendre connection");
		  
		Emetteu=new Socket(InetAddress.getByName(Nom_Machine),Numero_port);
		  //Emetteu.setSoTimeout(9000);
		System.out.println("\nConnected to " + Emetteu.getInetAddress().getHostName());
		System.out.println();

	}
	
	private void getStream() throws IOException{
		output= new ObjectOutputStream(Emetteu.getOutputStream());
		output.flush();
		
		input= new ObjectInputStream(Emetteu.getInputStream());
}
	
   private void processConnection()throws IOException, InterruptedException{
       //Trames trame=new Trames("01111110","111111",1,"00000111","1100001");
     //sendTrame(trame);
	   String type="";
      do {
    	  try {
    		  String meg=(String)input.readObject();
    		  
    		  System.out.println(meg);
    		 Trames tra=(Trames)input.readObject();
    		 
     		 String flag=tra.flag;
     		 type=tra.type;
     		 String Num=tra.Num;
     		 String CRC=tra.CRC;
     		 String Donnees=tra.Donnees;
     		 String to_String=tra.to_String();
     		 String chaineVerifier=type+Num+Donnees+CRC;
     		 Emetteu.setSoTimeout(100000);
	         Thread.sleep(3000);
     		 
     		 System.out.println("Accusé de reception: Trame " +Num+" recu Merci");
     		 verif.afficheString(to_String);
     		 System.out.println();
    		  
    	  }
    	  catch(ClassNotFoundException classNotFoundException) {
    		  System.out.println("\nErreur de reception");
    	  }
      } while(true);//(!message.equals("CLIENNT>>>>Terminate connection"));	
      
     
   }
   
   private void EnvoyerLesTrames() throws IOException, InterruptedException {
	   //n'oublier pas d'ajouter le bit stuffing sur les donnees.
	   lire=new LireFichier();
	   verif=new CalculCrc();
	   bitSt=new bitStuffing();
	   String donner=lire.Donner(fich);
	   //System.out.println(donner);
	   String poly="1000100000010001";
	   String crc;
	   String donnee,donne;
	   String flag="01111110";
	   String paquet="";
	   Trames[] tabl=new Trames[8];
	   for(int i=goBack; i<8; i++) {
		   String chaineBit=lire.convertir(i);
		  
		   switch(i) {
		   case 0:
			   donnee=01000011+chaineBit+donner;
			   crc=verif.checkSum(donnee,poly);
			   paquet="01000011"+chaineBit+donner+crc;
			    donne=bitSt.bitsStuffing(paquet);
		    Trames trame=new Trames(flag,"01000011",chaineBit,donner,crc,donne);
		    
		    //System.out.println(paquet);
			  // System.out.println(donne);
		     //Trames tram=new Trames(flag,donne,trame);
		     tabl[i]=trame;
		           // sendTrame(tram);
		           
		            //System.out.println("tu restes ici");
		        	//processConnection();
		        //	System.out.println("sortir1");
		            break;
		   case 1:
			   donnee=01001001+chaineBit+donner;
			   crc=verif.checkSum(donnee,poly);
			   paquet="01001001"+chaineBit+donner+crc;
			   donne=bitSt.bitsStuffing(paquet);
			    Trames trame1=new Trames(flag,"01001001",chaineBit,donner,crc,donne);
			   
			  // System.out.println(paquet);
			   //System.out.println(donne);
			   //System.out.println();
			    // Trames tram1=new Trames(flag,donne,trame1);
			    //sendTrame(tram1);
			     tabl[i]=trame1;
				//processConnection();
				
			    break;
		   case 2:
			   donnee=01001001+chaineBit+donner;
			   crc=verif.checkSum(donnee,poly);
			   paquet="01001001"+chaineBit+donner+crc;
			   donne=bitSt.bitsStuffing(paquet);
			   Trames trame2=new Trames(flag,"01001001",chaineBit,donner,crc,donne);
			  
			   //System.out.println(paquet);
			   //System.out.println(donne);
			   //System.out.println();
			   //Trames tram2=new Trames(flag,donne,trame2);
			   //sendTrame(tram2);
			    tabl[i]=trame2;
				//processConnection();
				//System.out.println("sorti2");
			   break;
		   case 3:
			   donnee=01001001+chaineBit+donner;
			   crc=verif.checkSum(donnee,poly);
			   paquet="01001001"+chaineBit+donner+crc;
			   donne=bitSt.bitsStuffing(paquet);
			   Trames trame3=new Trames(flag,"01001001",chaineBit,donner,crc,donne);
			  
			   //System.out.println(paquet);
			   //System.out.println(donne);
			   //System.out.println();
			    // Trames tram3=new Trames(flag,donne,trame3);
			   //sendTrame(tram3);
			     tabl[i]=trame3;
				//processConnection();
			   break;
		    case 4:
		    	donnee=01001001+chaineBit+donner;
				crc=verif.checkSum(donnee,poly);
				 paquet="01001001"+chaineBit+donner+crc;
				 donne=bitSt.bitsStuffing(paquet);
			    Trames trame4=new Trames(flag,"01001001",chaineBit,donner,crc,donne);
			   
				//System.out.println(paquet);
				   
				  //System.out.println(donne);
				  // System.out.println();
			     //Trames tram4=new Trames(flag,donne,trame4);
				//sendTrame(tram4);
			     tabl[i]=trame4;
				//processConnection();
				break;
			case 5:
				donnee=01001001+chaineBit+donner;
			    crc=verif.checkSum(donnee,poly);
			    paquet="01001001"+chaineBit+donner+crc;
			    donne=bitSt.bitsStuffing(paquet);
			    Trames trame5=new Trames(flag,"01001001",chaineBit,donner,crc,donne);
			   
			    //System.out.println(paquet);
			
				  // System.out.println(donne);
				   //System.out.println();
			     //Trames tram5=new Trames(flag,donne,trame5);
			    //sendTrame(tram5);
			     tabl[i]=trame5;
				//processConnection();
			    break;  
			case 6:
				donnee=01001001+chaineBit+donner;
				crc=verif.checkSum(donnee,poly);
				 paquet="01010000"+chaineBit+donner+crc;
				 donne=bitSt.bitsStuffing(paquet);
			    Trames trame6=new Trames(flag,"01010000",chaineBit,donner,crc,donne);
			   
				 //System.out.println(paquet);
			
				//   System.out.println(donne);
				  // System.out.println();
			     //Trames tram6=new Trames(flag,donne,trame6);
				//sendTrame(tram6);
			     tabl[i]=trame6;
				//processConnection();
			    break;
		
			case 7:
				donnee=01000110+chaineBit+donner;
				crc=verif.checkSum(donnee,poly);
				paquet="01000110"+chaineBit+donner+crc;
				 donne=bitSt.bitsStuffing(paquet);
			    Trames trame7=new Trames(flag,"01000110",chaineBit,donner,crc,donne);
			    
				 //System.out.println(paquet);
				
				   //System.out.println(donne);
				   //System.out.println();
			     //Trames tram7=new Trames(flag,donne,trame7);
				//sendTrame(tram8);
			     tabl[i]=trame7;
				//processConnection();
				break;
				
				default:
					System.out.println("Erreur d'envoie d'une trame");
						  		   
		   }
		   
	   }
	  
	   sendTrame(tabl);
	   processConnection();
   }
   
   public void closeConnection() {
	 

	   try {
		   //output.close();
		   input.close();
		   Emetteu.close();
	   }
	   catch(IOException ioException) {
		   ioException.printStackTrace();
	   }
   }
   
   private void sendTrame(Trames[] tram) {
	   
	   try {
		 
		  
		   output.writeObject(tram);
		   output.flush();
		   //System.out.println("Trame envoyé" );
		   //verif.afficheString(tram.to_String());
		   //System.out.println();
	   }
	   catch(IOException ioException) {
		   System.out.println("Erreur d'envoie "+ioException);
	   
	   }
   }
	
}