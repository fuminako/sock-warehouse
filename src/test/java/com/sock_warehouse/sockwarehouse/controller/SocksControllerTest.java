package com.sock_warehouse.sockwarehouse.controller;

import com.sock_warehouse.sockwarehouse.constant.ComparisonOperators;
import com.sock_warehouse.sockwarehouse.dto.SocksDto;
import com.sock_warehouse.sockwarehouse.entity.Sock;
import com.sock_warehouse.sockwarehouse.repository.SocksRepository;
import com.sock_warehouse.sockwarehouse.service.SocksService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public class SocksControllerTest {
    private final MockMvc mockMvc;
    @MockBean
    private SocksService service;
    @MockBean
    private SocksRepository repository;

    @Autowired
    SocksControllerTest(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void socksIncomeTest1() throws Exception {
        SocksDto socksDto = new SocksDto();
        socksDto.setId(1L);
        socksDto.setColor("yellow");
        socksDto.setQuantity(100);
        socksDto.setCottonPart(10);

        JSONObject socksObject = new JSONObject();
        socksObject.put("id", 1L);
        socksObject.put("color", "yellow");
        socksObject.put("cottonPart", 10);
        socksObject.put("quantity", 100);

        when(service.socksIncome(any(SocksDto.class))).thenReturn(socksDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .content(socksObject.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.color").value("yellow"))
                .andExpect(jsonPath("$.cottonPart").value(10))
                .andExpect(jsonPath("$.quantity").value(100));
    }

    @Test
    public void socksIncomeTest2() throws Exception {
        Sock socks = new Sock(1, 20, "green", 700);
        repository.save(socks);

        SocksDto socksDto = new SocksDto();
        socksDto.setId(1L);
        socksDto.setColor("green");
        socksDto.setQuantity(1400);
        socksDto.setCottonPart(20);

        JSONObject socksObject = new JSONObject();
        socksObject.put("id", 1);
        socksObject.put("quantity", 700);

        when(service.socksIncome(any(SocksDto.class))).thenReturn(socksDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .content(socksObject.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.color").value("green"))
                .andExpect(jsonPath("$.cottonPart").value(20))
                .andExpect(jsonPath("$.quantity").value(1400));
    }

    @Test
    void socksIncomeTest3() throws Exception {

        SocksDto socksDto = new SocksDto();
        socksDto.setId(1L);
        socksDto.setColor("green");
        socksDto.setQuantity(1400);
        socksDto.setCottonPart(20);

        JSONObject socksObject = new JSONObject();
        socksObject.put("id", 1);
        socksObject.put("quantity", 700);

        when(service.socksIncome(any(SocksDto.class))).thenReturn(socksDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .content(socksDto.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void socksOutcomeTest1() throws Exception {
        SocksDto socksDto = new SocksDto();
        socksDto.setId(1L);
        socksDto.setColor("yellow");
        socksDto.setQuantity(100);
        socksDto.setCottonPart(10);

        JSONObject socksObject = new JSONObject();
        socksObject.put("id", 1L);
        socksObject.put("color", "yellow");
        socksObject.put("cottonPart", 10);
        socksObject.put("quantity", 100);

        when(service.socksOutcome(any(SocksDto.class))).thenReturn(socksDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/outcome")
                        .content(socksObject.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.color").value("yellow"))
                .andExpect(jsonPath("$.cottonPart").value(10))
                .andExpect(jsonPath("$.quantity").value(100));
    }

    @Test
    public void socksOutcomeTest2() throws Exception {
        Sock socks = new Sock(1, 20, "green", 700);
        repository.save(socks);

        SocksDto socksDto = new SocksDto();
        socksDto.setId(1L);
        socksDto.setColor("green");
        socksDto.setQuantity(0);
        socksDto.setCottonPart(20);

        JSONObject socksObject = new JSONObject();
        socksObject.put("id", 1);
        socksObject.put("quantity", 700);

        when(service.socksOutcome(any(SocksDto.class))).thenReturn(socksDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/outcome")
                        .content(socksObject.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.color").value("green"))
                .andExpect(jsonPath("$.cottonPart").value(20))
                .andExpect(jsonPath("$.quantity").value(0));
    }

    @Test
    void socksOutcomeTest3() throws Exception {

        SocksDto socksDto = new SocksDto();
        socksDto.setId(1L);
        socksDto.setColor("green");
        socksDto.setQuantity(1400);
        socksDto.setCottonPart(20);

        JSONObject socksObject = new JSONObject();
        socksObject.put("id", 1);
        socksObject.put("quantity", 700);

        when(service.socksOutcome(any(SocksDto.class))).thenReturn(socksDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/outcome")
                        .content(socksDto.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void socksQuantity1() throws Exception {
        Sock socks1 = new Sock(1, 20, "green", 700);
        repository.save(socks1);
        Sock socks2 = new Sock(1, 20, "green", 700);
        repository.save(socks2);

        when(service.getSocks(anyString(), any(ComparisonOperators.class), anyInt())).thenReturn("1400");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/socks")
                        .param("color", "green")
                        .param("operation", String.valueOf(ComparisonOperators.lessThan))
                        .param("cottonPart", "20"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1400"));
    }
}
