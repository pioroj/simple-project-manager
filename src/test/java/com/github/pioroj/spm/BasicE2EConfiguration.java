package com.github.pioroj.spm;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
abstract public class BasicE2EConfiguration {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    protected MongoTemplate mongoTemplate;

    @BeforeEach
    void setup() {
        clearMongoDb();
    }

    protected ResponseEntity post(String url, Object requestBody) {
        return sendRequest(url, HttpMethod.POST, requestBody, Object.class);
    }

    private <T> ResponseEntity<T> sendRequest(String url, HttpMethod httpMethod, T requestBody, Class<T> responseBodyType) {
        HttpEntity httpEntity = new HttpEntity(requestBody);
        return testRestTemplate.exchange(url, httpMethod, httpEntity, responseBodyType);
    }

    private void clearMongoDb() {
        for (String collection : mongoTemplate.getCollectionNames()) {
            mongoTemplate.dropCollection(collection);
        }
    }
}
