package cn.com.dormant.service.core.security.rbac.services;

public class UserMgtServiceException extends Exception{
	private static final long serialVersionUID = -1710194055284302040L;
	
	private String message 	= "";
	
	public UserMgtServiceException(String message) {
		this.message = message;
	}
	
	public UserMgtServiceException(String message, Throwable e) {
		this.message = message;
		this.initCause(e);
	}
	
	public void setCause(Throwable e){
		this.initCause(e);
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return this.message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		String cls = getClass().getName();
        return cls + ": " + message;
	}
}
