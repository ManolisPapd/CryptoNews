package net.manolispapadimitriou.cryptonewsbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.manolispapadimitriou.cryptonewsbackend.model.Portfolio;
import net.manolispapadimitriou.cryptonewsbackend.model.User;
import net.manolispapadimitriou.cryptonewsbackend.repository.PortfolioRepository;
import net.manolispapadimitriou.cryptonewsbackend.repository.UserRepository;
import net.manolispapadimitriou.cryptonewsbackend.service.Mapper;
import net.manolispapadimitriou.cryptonewsbackend.viewmodel.PortfolioViewModel;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PortfolioControllerTest {


    private MockMvc mockMvc;

    @Mock
    private PortfolioRepository portfolioRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private PortfolioController portfolioController;
    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private ObjectNode objectNode;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(portfolioController)
                .build();
    }

    @Test
    public void testAllPortfolio() throws Exception {
        List<Portfolio> portfolioList = new ArrayList<>();
        User user = new User();
        user.setId(1);
        Portfolio portfolio = new Portfolio("euro","19/19/2001","90",user);
        portfolioList.add(portfolio);

        when(portfolioRepository.findAll()).thenReturn(portfolioList);

        mockMvc.perform(
                get("/portfolio/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].currency", Matchers.is("euro")));

        verify(portfolioRepository).findAll();
    }

    @Test
    public void testNewCurrency() throws Exception{
        PortfolioViewModel portfolioViewModel = new PortfolioViewModel("ripple","1/1/2018","0.23","admin");


        objectNode = objectMapper.createObjectNode();
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(portfolioViewModel);




        mockMvc.perform(
                post("/portfolio/currency")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.timestamp",Matchers.is(nullable(String.class))))
                .andExpect(jsonPath("$.status",Matchers.is(200)))
                .andExpect(jsonPath("$.message",Matchers.is("currency added to the database")));


    }





}