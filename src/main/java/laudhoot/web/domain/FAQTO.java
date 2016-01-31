package laudhoot.web.domain;

import laudhoot.core.domain.FAQ;

public class FAQTO {
	
	private String question;
	
	private String answer;
	
	public FAQTO(FAQ faq) {
		super();
		this.question = faq.getQuestion();
		this.answer = faq.getAnswer();
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
