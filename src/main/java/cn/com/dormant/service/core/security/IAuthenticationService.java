package cn.com.dormant.service.core.security;

public interface IAuthenticationService {
	/*
	 * UserID/Password, Finger System, Card System
	 */
	public AuthenticationInfo authenticate(AuthToken authToken);
}
