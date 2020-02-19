package ru.javawebinar.topjava.web;

import org.junit.jupiter.api.Test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ResourceControllerTest extends AbstractControllerTest {

    @Test
    void resourceTest() throws Exception {
        mockMvc.perform(get("/resources/css/style.css"))
                .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType("text/css"));
    }
}
