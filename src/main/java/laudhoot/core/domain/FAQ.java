package laudhoot.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * A class to represent frequently asked questions.
 * 
 * @author apurve
 * */

@Entity
public class FAQ extends BaseDomain {
	
	@Lob
	@Column(length = 512)
	private String question;
	
	@Lob
	@Column(length = 2048)
	private String answer;
	
	public FAQ() {
		super();
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
