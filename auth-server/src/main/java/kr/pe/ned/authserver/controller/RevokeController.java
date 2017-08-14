package kr.pe.ned.authserver.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user/tokens")
public class RevokeController {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ConsumerTokenServices tokenServices;

    @GetMapping()
    public Collection<OAuth2AccessToken> list() {
        Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId("acme");
        return tokens;
    }

    @RequestMapping("revoke/{tokenId}")
    public Map<String, Object> revokeToken(@PathVariable("tokenId") String tokenId) {
        tokenServices.revokeToken(tokenId);

        Map<String, Object> result = new HashMap<>();
        result.put("token", tokenId);
        return result;
    }

    @RequestMapping("revokerefreshtoken/{tokenId}")
    public Map<String, Object> revoke(@PathVariable("tokenId") String tokenId) {

        DefaultOAuth2RefreshToken oAuth2RefreshToken = new DefaultOAuth2RefreshToken(tokenId);
        tokenStore.removeRefreshToken(oAuth2RefreshToken);

        Map<String, Object> result = new HashMap<>();
        result.put("token", tokenId);

        return result;
    }




}
