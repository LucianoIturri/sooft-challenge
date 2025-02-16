package org.application.transfer;

import org.domain.port.transfer.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    private final TransferRepository repository;

    @Autowired
    public TransferService(TransferRepository repository) {
        this.repository = repository;
    }
}
