package laudhoot.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="shout")
public class Shout extends BaseCoreDomain{
	
	public String testText;
	
	public Shout(){
		
	}

	public String getTestText() {
		return testText;
	}

	public void setTestText(String testText) {
		this.testText = testText;
	}
	
}
