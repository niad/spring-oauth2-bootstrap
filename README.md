# 

# 프로젝트 생성

    $ mkdir    
    $ curl https://start.spring.io/starter.tgz -d style=web -d style=security -d name=authserver | tar -xzvf -


# 인증 - 웹브라우저 기반

웹 브라우저 인증코드 획득 - redirect 방식을 사용

    http://localhost:9999/uaa/oauth/authorize?response_type=code&client_id=acme&redirect_uri=http://example.com
    
주소입력시 Basic-Auth를 이용한 login 창이 실행되고, scope를 선택해서 승인하는 화면이 출력된다.

승인버튼을 클릭하면 요청시 입력한 redirect_uri로 이동한다.

redirect 시에 application 에서 접근가능한 code를 반환함 
장점은 clientId와 clientSecret이 노출될 일이 없음

    http://example.com/?code=TYEQae
    (code=TYEQae 은 생성된 값)

반환된 code를 이용해  
    
    $ curl acme:acmesecret@localhost:9999/uaa/oauth/token -d grant_type=authorization_code -d client_id=acme -d redirect_uri=http://example.com -d code=TYEQae
    
    {
        "access_token":"9fde527e-e71a-4ca2-a8f2-84422f60f79a",
        "token_type":"bearer",
        "refresh_token":"e8590786-89d0-4695-8706-e8dcc22d1045",
        "expires_in":43199,
        "scope":"openid"
    }
    

    // 이건 안되네.. 왜지? ({"error":"method_not_allowed","error_description":"Request method &#39;GET&#39; not supported"})
    POST 호출
    http://acme:acmesecret@localhost:9999/uaa/oauth/token 
        grant_type=authorization_code&client_id=acme&code=2TPmvP&redirect_uri=http://example.com

    $ curl acme:acmesecret@localhost:9999/uaa/oauth/token -d grant_type=authorization_code -d client_id=acme -d redirect_uri=http://example.com -d code=2TPmvP
    {
        "access_token":"b23df809-0a0e-4af4-a007-31c43f6ef18c",
        "token_type":"bearer",
        "refresh_token":"fd1e9ed3-4d17-434d-89a0-5a47efee1a58",
        "expires_in":43199,
        "scope":"openid"
    }
    
    
# 인증 - token 기반 
    
    
    
    
# 정보 요청

    $TOKEN 의 위에서 입력한 획득한 access_token 값
    
    $ curl -H "Authorization: Bearer $TOKEN" localhost:9000
    
    
    
    
    
# UI 서버 작성

    http://localhost:8080/

# TODO

    logout 후 login 시 401 오류 발생
    
    
# 여러가지 Grant Type

## Password
사용자 token

Authorication : Basic clientCredential ( base64(clientId:clientPassword) )

POST http://localhost:9999/oauth/token
    grant_type=password&
    username=USERNAME&
    password=PASSWORD&
    client_id=CLIENT_ID

{
    "access_token": "73f52a60-2622-42cd-b69a-a1fe5c1cdd30",
    "token_type": "bearer",
    "refresh_token": "397196bf-53bb-43b6-b8e3-ecf229770a10",
    "expires_in": 42446,
    "scope": "openid"
}


## refresh token

POST http://localhost:9999/oauth/token
    grant_type=refresh_token&
    refresh_token=REFRESH_TOKEN&
    client_id=CLIENT_ID




## Application Access
사용자가 로그인 하지 않았지만 client 애플리케이션 인증, 무작위 api 호출이 일어나지 않도록 클라이언트 앱의 기본인증

POST http://localhost:9999/oauth/token
    grant_type=client_credentials&
    client_id=CLIENT_ID&
    client_secret=CLIENT_SECRET



## TODO
Zuul + OAuth2 - REST API Server





# Reference

[위 예제의 원본](https://github.com/spring-guides/tut-spring-security-and-angular-js/tree/master/oauth2-vanilla)

[OAuth2](https://aaronparecki.com/oauth-2-simplified/)
Authorization grant types
    
- Authorization Code : Web server, Browser-based app, mobile apps
- Password : username & password
- Client credentials : application access
- Implicit


