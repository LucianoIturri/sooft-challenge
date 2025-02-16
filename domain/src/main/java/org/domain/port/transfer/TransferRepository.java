package org.domain.port.transfer;

import org.domain.model.transfer.Transfer;

public interface TransferRepository {
    public void save(Transfer transfer);
}
