package org.transport.v1.transfer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.application.enterprise.mapper.EnterpriseDTO;
import org.application.transfer.TransferService;
import org.application.transfer.mapper.TransferDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransferControllerTest {
    private MockMvc mockMvc;

    @Mock
    private TransferService service;

    @InjectMocks
    private TransferController controller;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldCreateTransferSuccessfully() throws Exception {
        TransferDTO requestDTO = TransferDTO.builder()
                .id(1)
                .date(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .amount(Float.parseFloat("1000"))
                .debitAccount("0800")
                .creditAccount("0900")
                .enterpriseId(1)
                .build();

        TransferDTO responseDTO = TransferDTO.builder()
                .id(1)
                .date(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .amount(Float.parseFloat("1000"))
                .debitAccount("0800")
                .creditAccount("0900")
                .enterpriseId(1)
                .build();

        when(service.createTransfer(any(TransferDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/v1/transfer/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(Float.parseFloat("1000")))
                .andExpect(jsonPath("$.credit_account").value("0900"))
                .andExpect(jsonPath("$.debit_account").value("0800"))
                .andExpect(jsonPath("$.date").value(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                .andExpect(jsonPath("$.enterprise_id").value(1));
    }

    @Test
    public void shouldNotCreateTransfer_Failure_NullOrEmptyRequiredParams() throws Exception {
        TransferDTO requestDTO = TransferDTO.builder()
                .amount(null)
                .debitAccount("")
                .creditAccount("")
                .enterpriseId(null)
                .build();

        mockMvc.perform(post("/api/v1/transfer/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().is4xxClientError());
    }
}
