package org.application.transfer;

import org.application.transfer.mapper.TransferDTO;
import org.domain.model.enterprise.Enterprise;
import org.domain.model.transfer.Transfer;
import org.domain.port.transfer.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class TransferService {
    private final TransferRepository repository;

    @Autowired
    public TransferService(TransferRepository repository) {
        this.repository = repository;
    }

    public TransferDTO createTransfer(TransferDTO transferDTO) {
        /*
         * Create a new company transfer with all their needed fields
         * @param TransferDTO
         * @return TransferDTO
         * */
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        transferDTO.setDate(date);
        Transfer transfer = mapper(transferDTO);
        repository.save(transfer);
        return transferDTO;
    }

    public Transfer mapper(TransferDTO transferDTO) {
        /*
         * Application layer is the intermediary between transport layer and domain layer, we use a DTO to
         * transport info, and then we need to parse in their "associated" object, in this case: Transfer
         * @param TransferDTO
         * @return Transfer
         * */
        return Transfer.builder()
                .id(transferDTO.getId())
                .amount(transferDTO.getAmount())
                .creditAccount(transferDTO.getCreditAccount())
                .debitAccount(transferDTO.getDebitAccount())
                .date(transferDTO.getDate())
                .enterprise(Enterprise.builder().id(transferDTO.getEnterpriseId()).build())
                .build();
    }
}
