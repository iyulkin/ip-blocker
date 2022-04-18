package com.khakimova.ipblocker;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    private MockMvc mvc;

    private final Random random = new Random();

    @RepeatedTest(10)
    @DisplayName("GET / success")
    void testThatFirstNRequestsFromOneIpGets200() throws Exception {

        String ip = String.format("%s.%s.%s.%s", randomInt(), randomInt(), randomInt(), randomInt());
            mvc.perform(get("/").header("X-REAL-IP", ip))
                    .andExpect(status().isOk());
    }

    private int randomInt() {
        return random.nextInt(1000);
    }
}
