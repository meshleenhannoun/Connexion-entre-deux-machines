import java.io.Serializable;

public class Trames implements Serializable {
	protected String flag;
	protected String type;
	protected String Num;
	protected String Donnees;
	protected String CRC;//cela va etre de type classe.
	protected String donne;

	
	
	
	public Trames(String flag, String type,String Num, String Donnees, String CRC,String donner) {
		this.flag=flag;
		this.type=type;
		this.Num=Num;
		this.Donnees=Donnees;
		this.CRC=CRC;
		this.donne=donner;
		
	}
	
	
	public String to_String1() {
		return flag+donne+flag;
	}
public String to_String() {
	return flag+type+Num+Donnees+CRC+flag;
}
public String getDonne() {
	return donne;
}
public void setType(String type) {
	this.type = type;
}
public void setDonne(String donne) {
	this.donne = donne;
}
}