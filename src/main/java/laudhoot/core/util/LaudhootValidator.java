package laudhoot.core.util;


import org.springframework.util.StringUtils;

import laudhoot.core.domain.BaseDomain;

public class LaudhootValidator {

	/**
	 * Checks if the domain is persisted or not.
	 * 
	 * @param domain - the domain which needs to be checked for persistence.
	 * @param exceptionMessage
	 * 
	 * @return boolean true if persisted, otherwise throws {@link LaudhootValidationException}.
	 * 
	 * @throws LaudhootValidationException
	 * */
	public static boolean isPersisted(BaseDomain domain, String exceptionMessage) throws LaudhootValidationException {
		if(isPersisted(domain)){
			return true;
		}
		throw getValidationException(exceptionMessage);
	}

	/**
	 * Checks if the domain is persisted or not.
	 * 
	 * @param domain - the domain which needs to be checked for persistence.
	 * 
	 * @return true if persisted, otherwise false.
	 * */
	public static boolean isPersisted(BaseDomain domain) {
		return isNotNull(domain.getId());
	}
	
	/**
	 * Checks if the object is not null.
	 * 
	 * @param object - the object which needs to be checked for not null.
	 * @param exceptionMessage
	 * 
	 * @return boolean true if null, otherwise throws {@link LaudhootValidationException}.
	 * 
	 * @throws LaudhootValidationException
	 * */
	public static boolean isNotNull(Object object, String exceptionMessage) throws LaudhootValidationException {
		if(isNotNull(object)){
			return true;
		}
		throw getValidationException(exceptionMessage);
	}

	/**
	 * Checks if the object is not null.
	 * 
	 * @param object - the object which needs to be checked for not null.
	 * 
	 * @return true if null, otherwise false.
	 * */
	public static boolean isNotNull(Object object) {
		return !isNull(object);
	}
	
	/**
	 * Checks if the object is null or not.
	 * 
	 * @param object - the object which needs to be checked for null.
	 * @param exceptionMessage
	 * 
	 * @return boolean true if null, otherwise throws {@link LaudhootValidationException}.
	 * 
	 * @throws LaudhootValidationException
	 * */
	public static boolean isNull(Object object, String exceptionMessage) throws LaudhootValidationException {
		if(isNull(object)){
			return true;
		}
		throw getValidationException(exceptionMessage);
	}

	/**
	 * Checks if the object is null or not.
	 * 
	 * @param object - the object which needs to be checked for null.
	 * 
	 * @return true if null, otherwise false.
	 * */
	public static boolean isNull(Object object) {
		return object == null;
	}
	
	private static LaudhootValidationException getValidationException(String exceptionMessage)
			throws LaudhootValidationException {
		if(!StringUtils.isEmpty(exceptionMessage)){
			return new LaudhootValidationException();
		}else{
			return new LaudhootValidationException(exceptionMessage);
		}
	}

}

