import java.io.IOException;
import java.net.Socket;

	public class TestErreur {
		private static String crc;
		private  static String type;
		private static int temps;
		private static String Données;
		private String chainee;
	
		
		public TestErreur(String crc, String type, String données) {
			super();
			this.crc = crc;
			this.type = type;
			this.temps = temps;
			Données = données;
			this.chainee="";
			
			
		}
	 	
	
		public boolean TrameErronée(String donner,String crc,String message) {
			
			String chaineC= VerifierTram(donner, crc);
			if(chaineC.equals("000000000000000")) {
				
				return true;
			}
			else {
				return false;
			}
		}
		
		public void TramPerdu(int Num) {
			
			
		}
		public boolean Type(String type) {
			boolean etat=false;
			switch(type) {
			case "01000011":
				etat=true;
				chainee="Demande de connection";
			   break;
			case "01001001":
				etat=true;
				chainee="Demande d'information";
				break;
			case "01000110":
				etat=true;
				chainee="Fermeture de connection";
				
				break;
			default:
				etat=true;
				chainee="Mauvais type";
				break;
			}
			return etat; 
			
		}

	
	public String getChainee() {
			return chainee;
		}


	public String additionChainBit(String chain1,String chain2) {
		String chaine="";
		
		for(int i=0; i<chain1.length(); i++) {
			char cha1=chain1.charAt(i);
			char cha2=chain2.charAt(i);
			if(cha1==cha2) {
				chaine=chaine+0;
			}
			else {
				chaine=chaine+1;
			}
			
		}
		return chaine;
	}
	
	public String ajoutZeroDansChaine(String chain1, String chain2) {
		String chaine=chain1;
		for(int i=0; i<chain2.length()-1;i++) {
			chaine=chaine+0;
			
		}
		return chaine;
		
	}
	
	public String VerifierTram(String chain1,String chain2) {
		String chaineZ=chain2;
		String chai;
		String chaineP=chaineZ.substring(0, chain2.length());
		String chaineAd="";
		
		
		 chaineAd=retirerZeroAdd(chaineP,chain2);
		 
		for(int i=chain2.length(); i<chaineZ.length(); i++) {
			 chai=chaineAd+chaineZ.charAt(i);
			
			 
			if(chai.charAt(0)=='1') {
				chaineAd=retirerZeroAdd(chai,chain2);
				
			}
			else if(chai.charAt(0)=='0')  {
				chaineAd=retirerZeroAdd(chai,genererZero(chain2));
				
			}
			 chai=chaineAd+chaineZ.charAt(i);
		 
		}
		
		return chaineAd;
			
	}
	

    public String retirerZeroAdd(String chain,String chain2) {
    	String chaineR=additionChainBit(chain,chain2);
    	String rep=chaineR.substring(1, chain2.length());
    	
    	return rep;
    	
    }
	public String genererZero(String chain2) {
		String chain="";
		for(int i=0; i<chain2.length(); i++) {
			chain=chain+0;
		}
		return chain;
	}
	
	/*public static void main(String[] args) {
		afficheString("1111111111111111111111111111111111111111111111111111111111110000000000000000000000000000000111111111111101011111111111111111");
		
	}*/
		
			
}
	