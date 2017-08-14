# 

# 프로젝트 생성

    $ mkdir    
    $ curl https://start.spring.io/starter.tgz -d style=web -d style=security -d name=authserver | tar -xzvf -


# 인증코드 획득

    웹 브라우저
    http://localhost:9999/uaa/oauth/authorize?response_type=code&client_id=acme&redirect_uri=http://example.com
    
    redirect 
    http://example.com/?code=TYEQae
    (code=TYEQae 은 생성된 값)

    
    $ curl acme:acmesecret@localhost:9999/uaa/oauth/token -d grant_type=authorization_code -d client_id=acme -d redirect_uri=http://example.com -d code=TYEQae

    {
        "access_token":"9fde527e-e71a-4ca2-a8f2-84422f60f79a",
        "token_type":"bearer",
        "refresh_token":"e8590786-89d0-4695-8706-e8dcc22d1045",
        "expires_in":43199,
        "scope":"openid"
    }
    
# 정보 요청

    $TOKEN 의 위에서 입력한 획득한 access_token 값
    
    $ curl -H "Authorization: Bearer $TOKEN" localhost:9000
    
    
# UI 서버 작성

    http://localhost:8080/

# TODO

    logout 후 login 시 401 오류 발생
    

# Reference

[스프링 OAuth2를 사용한 SSO - 오류업데이트안됨](http://springboot.tistory.com/8)
[위 예제의 원본](https://github.com/spring-guides/tut-spring-security-and-angular-js/tree/master/oauth2-vanilla)

