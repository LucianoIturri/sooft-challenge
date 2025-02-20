package org.transport.v1.enterprise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.application.enterprise.EnterpriseService;
import org.application.enterprise.mapper.EnterpriseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EnterpriseControllerTest {
    private MockMvc mockMvc;
    @Mock
    private EnterpriseService service;
    @InjectMocks
    private EnterpriseController controller;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void shouldCreateEnterpriseSuccessfully() throws Exception {
        EnterpriseDTO requestDTO = EnterpriseDTO.builder()
                .id(1)
                .cuit("12345")
                .companyName("AWESOME COMPANY")
                .accessionDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();

        EnterpriseDTO responseDTO = EnterpriseDTO.builder()
                .id(1)
                .cuit("12345")
                .companyName("AWESOME COMPANY")
                .accessionDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
        when(service.accession(requestDTO)).thenReturn(responseDTO);

        mockMvc.perform(post("/api/v1/enterprise/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cuit").value("12345"))
                .andExpect(jsonPath("$.name").value("AWESOME COMPANY"))
                .andExpect(jsonPath("$.accessionDate").value(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
    }

    @Test
    void shouldReturnLastEnterprisesTransfers() throws Exception {
        List<EnterpriseDTO> enterprises = Arrays.asList(
                EnterpriseDTO.builder()
                        .id(1)
                        .cuit("12345")
                        .companyName("AWESOME COMPANY")
                        .accessionDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .build(),
                EnterpriseDTO.builder()
                        .id(1)
                        .cuit("12345")
                        .companyName("AWESOME COMPANY II")
                        .accessionDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .build()
        );

        when(service.lastEnterprisesTransfers()).thenReturn(enterprises);

        mockMvc.perform(get("/api/v1/enterprise/last-transfers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("AWESOME COMPANY"))
                .andExpect(jsonPath("$[1].name").value("AWESOME COMPANY II"));
    }
}
