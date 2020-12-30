
public class CalculCrc {

	/*public static void main(String[] args) {
		String chaine=additionChainBit("00110011","11001101");
		String chaine1=ajoutZeroDansChaine("1101011011","10011");
		String chaine2=checkSum("1010001101","110101");
		String chaine3=retirerZeroAdd("00110011","11001101");
		String chaine4=genererZero("10011");
		String chaine5 =VerifierTram("1010001101","110101");
		System.out.println(chaine);
	
		System.out.println(chaine1);
		System.out.println(chaine2);
		System.out.println(chaine3);
		System.out.println(chaine4);
		System.out.println(chaine5);
		
		afficheString("1111111111111111111111111111111111111111111111111111111111110000000000000000000000000000000111111111111101011111111111111111");
		
	}*/
	
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
	
	
	public String checkSum(String chain1,String chain2) {
		String chaineZ=ajoutZeroDansChaine(chain1,chain2);
		String chai;
		String chaineP=chaineZ.substring(0, chain2.length());
		String chaineAd="";
		//System.out.println(chaineAd);
		
		 chaineAd=retirerZeroAdd(chaineP,chain2);
		 
		for(int i=chain2.length(); i<chaineZ.length(); i++) {
			 chai=chaineAd+chaineZ.charAt(i);
			 //System.out.println(chaineZ.charAt(i));
			 //System.out.println(chai);
			if(chai.charAt(0)=='1') {
				chaineAd=retirerZeroAdd(chai,chain2);
				//System.out.println(chaineAd+" "+i+"1");
			}
			else if(chai.charAt(0)=='0')  {
				chaineAd=retirerZeroAdd(chai,genererZero(chain2));
				//System.out.println(chaineAd+" "+i+"0");
			}
			 chai=chaineAd+chaineZ.charAt(i);
		    //System.out.println(i);
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
	
	public  void afficheString(String chaine) {
		
		String chaineR="";
		int j=0;
		int longu=chaine.length()/4;
		int l=2*longu;
		/*for(int i=0;i<5;i++) {
			if(i<=4) {
			
		chaineR=chaine.substring(j,longu+j);
		System.out.println(chaineR);
			}
			else {
				//System.out.println(chaine);
			}
		j=longu;
		}
		for(int i=0; i<4; i++) {
			if(i==0) {
				System.out.println(chaine.substring(0,longu));
			}
			else if(i<=2) {
				System.out.println(chaine.substring(longu,l));
				longu=2*longu+1;
				l=2*longu;
			}
			else if(i==3) {
		    System.out.println(chaine.substring(l,chaine.length()));
			}
		}*/
		System.out.println(chaine);
		
	
		
	}
}
