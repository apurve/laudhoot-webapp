package laudhoot.core.util;

@SuppressWarnings("serial")
public class LaudhootValidationException extends LaudhootException {
	
	public LaudhootValidationException(){
		super("Laudhoot system validation failure.");
	}
	
	public LaudhootValidationException(String message){
		super(message);
	}
}
