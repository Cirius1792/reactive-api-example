package com.example.assessment.controller;

import com.example.assessment.mapper.UserMapper;
import com.example.assessment.repository.GorestUserRepository;
import com.example.assessment.service.UserService;
import com.example.assessment.service.UserServiceImpl;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

@WireMockTest
class UserControllerImplTest {

    @Test
    void getUsers(WireMockRuntimeInfo wmRuntimeInfo) {
        WireMock wireMock = wmRuntimeInfo.getWireMock();
        String apiPath = "/users";
        wireMock.register(WireMock.get(apiPath)
                .willReturn(WireMock.okJson("[\n" +
                        "{\n" +
                        "\t\t\"id\": 3310,\n" +
                        "\t\t\"name\": \"Herbert Steuber\",\n" +
                        "\t\t\"email\": \"herbert_steuber@corkery.net\",\n" +
                        "\t\t\"gender\": \"female\",\n" +
                        "\t\t\"status\": \"inactive\"\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"id\": 3305,\n" +
                        "\t\t\"name\": \"Tasha Kris\",\n" +
                        "\t\t\"email\": \"kris_tasha@kozey.co\",\n" +
                        "\t\t\"gender\": \"male\",\n" +
                        "\t\t\"status\": \"inactive\"\n" +
                        "\t}" +
                        "]")));
        wireMock.register(WireMock.get(WireMock.urlPathEqualTo(apiPath)).withQueryParam("gender", WireMock.matching("male|female"))
                .willReturn(WireMock.okJson("[\n" +
                        "{\n" +
                        "\t\t\"id\": 3310,\n" +
                        "\t\t\"name\": \"Herbert Steuber\",\n" +
                        "\t\t\"email\": \"herbert_steuber@corkery.net\",\n" +
                        "\t\t\"gender\": \"female\",\n" +
                        "\t\t\"status\": \"inactive\"\n" +
                        "\t}" +
                        "]")));
        UserService service = new UserServiceImpl(
                new GorestUserRepository(wmRuntimeInfo.getHttpBaseUrl() + apiPath, new RestTemplate()),
                UserMapper.INSTANCE
        );
        UserController controller = new UserControllerImpl(service);
        Assertions.assertEquals(HttpStatus.OK, controller.getUsers(null).getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, controller.getUsers("male").getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.getUsers("xxx").getStatusCode());
    }
}