package io.crowdcode.speedbay.auction.web.controller;


import io.crowdcode.speedbay.auction.web.WebMvcConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WebMvcConfiguration.class)
@WebAppConfiguration
public class InfoControllerTest {

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;


    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetInfo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/{name}","JUnit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Every thing is fine, JUnit!"))
                .andDo(MockMvcResultHandlers.print());
    }
}