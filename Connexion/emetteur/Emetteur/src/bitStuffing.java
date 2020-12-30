
public class bitStuffing {

	/*public static void main(String[] args) {
		String chain=bitsStuffing("01010000000011001000011010001100100100101010000011001000110101101101011011011100110100001100010011101010110101001110110011000100110101001101000011000110111011001101010011010110110111001110110011010100010000001100010011101100110111001110110001000000110111001101101001000000111011001100010011010000110110100100000100111111110101"); 
				
		System.out.println(chain);
		
	}*/

	
	public String bitsStuffing(String chaine){
	                                   {
	           int compter=0;
	           String chaineRep="";
	              for(int i=0;i<chaine.length();i++)
	                {
	                   
	                   if (chaine.charAt(i)!='1' && chaine.charAt(i)!='0')
	                        {
	                            
	                           // return;
	                        }
	                   if(chaine.charAt(i) == '1')
	                        {
	                            compter++;
	                            chaineRep = chaineRep + chaine.charAt(i);
	                        }
	                   else
	                        {
	                            chaineRep = chaineRep + chaine.charAt(i);
	                            compter = 0;
	                        }
	                   if(compter == 5)
	                        {
	                            chaineRep =chaineRep + '0';
	                            compter = 0;
	                        }
	                }
	           
	            
	                                   
	                                   return chaineRep;
	                                   }
	       }

  public String desBitStuffing(String chaine) {
	  
	            int compter=0;
	            String chaineRep="";
	            
	            for(int i=0;i<chaine.length();i++)
	                {
	                   
	                    if(chaine.charAt(i) == '1')
	                        {
	                            
	                            compter++;
	                            chaineRep = chaineRep + chaine.charAt(i);
	                           
	                        }
	                    else
	                        {
	                             chaineRep = chaineRep + chaine.charAt(i);
	                             compter = 0;
	                        }
	                   if(compter == 5)
	                        {
	                              if((i+2)!=chaine.length())
	                              chaineRep = chaineRep + chaine.charAt(i+2);
	                              else
	                              chaineRep=chaineRep + '1';
	                              i=i+2;
	                              compter = 1;
	                        }
	                }
  
	          return chaineRep;
	       
}
}