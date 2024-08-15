package com.msd.restservice.controller;

import static org.mockito.Mockito.when;

import com.msd.restservice.dto.GithubOwnerDto;
import com.msd.restservice.service.OwnerDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {OwnerDetailController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class OwnerDetailControllerTest {
    @Autowired
    private OwnerDetailController ownerDetailController;

    @MockBean
    private OwnerDetailsService ownerDetailsService;

    @Test
    void testGetRepositoryDetails() throws Exception {

        when(ownerDetailsService.getGithubOwnerDetails(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(new GithubOwnerDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/repositories/{owner}/{repositoryName}",
                "Owner", "Repository Name");

        MockMvcBuilders.standaloneSetup(ownerDetailController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"full_name\":null,\"description\":null,\"clone_url\":null,\"stars\":0,\"created_at\":null}"));
    }
}
