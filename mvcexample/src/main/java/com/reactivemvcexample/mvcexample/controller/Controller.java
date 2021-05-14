package com.reactivemvcexample.mvcexample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Controller {

    @RequestMapping("/parallel")
    public Mono<Result> parallel() {
        return Flux.range(1, 10)
                .log()
                .flatMap(this::fetch, 4)
                .collect(Result::new, Result::add)
                .doOnSuccess(Result::stop);
    }

    private WebClient client = new WebClient(new ReactorHttpClientRequestFactory());

    private Mono<HttpStatus> fetch(int value) {
        return this.client.perform(HttpRequestBuilders.get("http://example.com"))
                .extract(WebResponseExtractors.response(String.class))
                .map(response -> response.getStatusCode());
    }

    @RequestMapping("/")
    public Mono<String> sayHello() {
        WebClient client = new WebClient(new ReactorHttpClientRequestFactory());
        return client.perform(HttpRequestBuilders.get("http://localhost:8080/traditional-controller"))
                .extract(WebResponseExtractors.response(String.class));
    }


}
