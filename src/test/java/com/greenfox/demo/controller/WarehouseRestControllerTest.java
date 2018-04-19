package com.greenfox.demo.controller;

import com.greenfox.demo.model.QuareyResult;
import com.greenfox.demo.model.Warehouse;
import com.greenfox.demo.repository.WarehouseRepository;
import com.greenfox.demo.service.WarehouseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WarehouseRestController.class})
@WebAppConfiguration
@EnableWebMvc
public class WarehouseRestControllerTest {

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(),
      Charset.forName("utf8"));

  private MockMvc mockMvc;

  @MockBean
  WarehouseRepository warehouseRepository;

  @MockBean
  WarehouseService warehouseService;

  //@MockBean
  //WarehouseRestController restController;

  //@MockBean
  //QuareyResult quareyResult;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void jsonResponseTest1() throws Exception {
    List<Warehouse> wareHouseList = Arrays.asList(new Warehouse(), new Warehouse());
    when(warehouseService.findItemsByParam(anyString(), anyFloat())).thenReturn(wareHouseList);

    mockMvc.perform(get("/warehouse/query").param("price", "50").param("type", "lower"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.clothes[0].id").value(0));
  }
}