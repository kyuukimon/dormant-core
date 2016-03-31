/*****************************************************************************
 * HISTORY
 *
 * 2014/09/22   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.security;

//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * <code>SecurityTest<code>
 *
 * @description: This class used to demo how to access Rest API
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/09/22
 * @version: 1.0
 */
public class SecurityTest {
//    public static void main(String[] args) {
//        /* Json example
//        {
//           "tenantId" : "orgId",
//           "userId" : "admin",
//           "password" :"admin"
//        }
//         */
//
//        //1. Get access token
//        String authToken = getAuthToken();
//        System.out.println("Access Token:" + authToken);
//
//        //2. Access RESTful API
//        System.out.println("Query host list");
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<Object> requestEntity = setHeader(authToken);
//
//        String url = "http://localhost:8080/dmormant-webapi/hosts";
//        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Map.class);
//        Map body = response.getBody();
//        System.out.println(body);
//
//        url = "http://localhost:8080/dormant-webapi/hosts/0";
//        response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Map.class);
//        body = response.getBody();
//        System.out.println(body);
//
//        //3. Log out
//
//
//
//    }
//
//    private static String getAuthToken() {
//        System.out.println("Get access token...");
//        PasswordRequestAuthToken token = new PasswordRequestAuthToken();
//        token.setTenantId("orgId");
//        token.setUserId("jgwang");
//        token.setPassword("passw0rd");
//        RestTemplate restTemplate = new RestTemplate();
//        Map x = restTemplate.postForObject(
//                "http://localhost:8080/dormant-webapi/tokens",
//                token,
//                Map.class
//        );
//
//        String auth_token = (String)x.get("auth_token");
//
//        return auth_token;
//    }
//
//    private static HttpEntity<Object> setHeader(String auth_token) {
//        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
//        headers.add("auth_token", auth_token);
//        HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
//        return requestEntity;
//    }
}
