# Intro

스프링 가이드의 튜토리얼중 Spring Security OAuth2 + Zuul 로 이루어진 예제 [oauth2-vanilla](https://github.com/spring-guides/tut-spring-security-and-angular-js/tree/master/oauth2-vanilla)는
웹 기반의 예제로 상당히 잘 작성되어 있다.

하지만, SESSION이 유지되지 않는 API call에서는 Full authentication이 이루어지지 않았다는 오류를 맞닥드리게 된다.

그 해결방법으로 SSO token을 릴레이로 Zull Proxy 내에 front end 부터 back end service 까지 전달해주는 Spring Cloud Security 가 있다.  



# REF
[Spring Cloud Security](https://cloud.spring.io/spring-cloud-security/)

[Authorization server behind Zuul](https://github.com/spring-cloud/spring-cloud-security/issues/94)