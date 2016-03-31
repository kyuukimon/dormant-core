package cn.com.dormant.service.core.security;

import java.util.List;

public interface IAuthorizationService {

    List<String> getRoles(String userId) throws AuthorizationException;

    boolean hasRole(String userId, String roleId) throws AuthorizationException;

    String getPermissions(String userId) throws AuthorizationException;

    boolean isPermited(String userId, String permission) throws AuthorizationException;

}
