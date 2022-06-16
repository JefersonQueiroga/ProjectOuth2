package br.com.solar.crm.projectouth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
public class TesteController {


    @Autowired
    private WebClient webClient;

    @GetMapping("/api/teste")
    public String teste(){
        return "Teste";
    }

    @GetMapping("/api/teste2")
    Mono<String> useOauthWithAuthCode() {
        Mono<String> retrievedResource = this.webClient.get()
                .uri("https://api.pipedrive.com/v1/users/me")
                .retrieve()
                .bodyToMono(String.class);

        System.out.println("--------------------------------------##########################");
        return retrievedResource.map(string ->
                "We retrieved the following resource using Oauth: " + string);
    }

    @GetMapping("/api/users")
    public String[] users(
            @RegisteredOAuth2AuthorizedClient("pipedrive")
            OAuth2AuthorizedClient client){
        return this.webClient
                .get()
                .uri("https://api.pipedrive.com/v1/users/me")
                .attributes(oauth2AuthorizedClient(client))
                .retrieve()
                .bodyToMono(String[].class)
                .block();
    }

}
