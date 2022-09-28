package com.example.assessment.repository;

import com.example.assessment.dto.UserDto;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@WireMockTest
class GorestUserRepositoryTest {


    @Test
    void findAll(WireMockRuntimeInfo wmRuntimeInfo) {
        // Start a Wiremock Server stubbing the gorest api.
        // The goal is to check that all the information of the original data source
        // are correctly deserialized
        WireMock wireMock = wmRuntimeInfo.getWireMock();
        String endpoint = wmRuntimeInfo.getHttpBaseUrl();
        String apiPath = "/users";
        wireMock.register(WireMock.get(apiPath)
                .willReturn(WireMock.okJson("[\n" +
                        "{\n" +
                        "    \"id\": 3310,\n" +
                        "    \"name\": \"Mario Rossi\",\n" +
                        "    \"email\": \"mariorossi@gmail.com\",\n" +
                        "    \"gender\": \"female\",\n" +
                        "    \"status\": \"inactive\"\n" +
                        "  }\n"+
                        "]")));
        UserRepository repo = new GorestUserRepository(endpoint + apiPath, new RestTemplate());
        List<UserDto> users = repo.findAll();
        Assertions.assertNotNull(users);
        Assertions.assertFalse(users.isEmpty());
        UserDto u = users.get(0);
        Assertions.assertEquals(3310L, u.getId());
        Assertions.assertEquals("Mario Rossi", u.getName());
        Assertions.assertEquals("mariorossi@gmail.com", u.getEmail());
        Assertions.assertEquals("female", u.getGender());
        Assertions.assertEquals("inactive", u.getStatus());
    }
    @Test
    void findByGender(WireMockRuntimeInfo wmRuntimeInfo) {
        // Start a Wiremock Server stubbing the gorest api.
        // The goal is to check that the query parameter is correctly managed
        WireMock wireMock = wmRuntimeInfo.getWireMock();
        String endpoint = wmRuntimeInfo.getHttpBaseUrl();
        String apiPath = "/users";
        wireMock.register(WireMock.get(WireMock.urlPathEqualTo(apiPath)).withQueryParam("gender", WireMock.matching("male|female"))
                .willReturn(WireMock.aResponse()));
        UserRepository repo = new GorestUserRepository(endpoint + apiPath, new RestTemplate());
        Assertions.assertDoesNotThrow(() -> repo.findByGender("male"));
        Assertions.assertDoesNotThrow(() -> repo.findByGender("female"));
    }
}