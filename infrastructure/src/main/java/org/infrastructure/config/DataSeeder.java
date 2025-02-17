package org.infrastructure.config;

import org.domain.model.enterprise.Enterprise;
import org.domain.model.transfer.Transfer;
import org.infrastructure.persistence.enterprise.EnterpriseRepositoryImpl;
import org.infrastructure.persistence.transfer.TransferRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    private final EnterpriseRepositoryImpl enterpriseRepository;
    private final TransferRepositoryImpl transferRepository;

    @Autowired
    public DataSeeder(EnterpriseRepositoryImpl enterpriseRepository, TransferRepositoryImpl transferRepository) {
        this.enterpriseRepository = enterpriseRepository;
        this.transferRepository = transferRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        String date = LocalDate.now().minusMonths(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Enterprise> enterprises = enterpriseRepository.newerEnterprises(date);

        if (!(enterprises.size() > 0)) {
            String accessionDate = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Enterprise e1 = Enterprise.builder()
                    .cuit("1234")
                    .accessionDate(accessionDate)
                    .companyName("Awesome Company I")
                    .build();
            Enterprise e2 =Enterprise.builder()
                    .cuit("56789")
                    .accessionDate(accessionDate)
                    .companyName("Awesome Company II")
                    .build();
            Enterprise e3 = Enterprise.builder()
                    .cuit("4321")
                    .companyName("Awesome Company III")
                    .accessionDate(accessionDate)
                    .build();
            Enterprise e4 = Enterprise.builder()
                    .cuit("98765")
                    .companyName("Awesome Company IV")
                    .accessionDate(accessionDate)
                    .build();
            enterpriseRepository.accession(e1);
            enterpriseRepository.accession(e2);
            enterpriseRepository.accession(e3);
            enterpriseRepository.accession(e4);

            Float amount = Float.valueOf("2000");

            transferRepository.save(Transfer.builder()
                    .enterprise(e1)
                    .amount(amount)
                    .creditAccount("4444")
                    .debitAccount("1234")
                    .date(dateBuilder(1))
                    .build());
            transferRepository.save(Transfer.builder()
                    .enterprise(e1)
                    .amount(amount)
                    .creditAccount("4444")
                    .debitAccount("1234")
                    .date(dateBuilder(1))
                    .build());
            transferRepository.save(Transfer.builder()
                    .enterprise(e2)
                    .amount(amount)
                    .creditAccount("4444")
                    .debitAccount("1234")
                    .date(dateBuilder(1))
                    .build());
            transferRepository.save(Transfer.builder()
                    .enterprise(e2)
                    .amount(amount)
                    .creditAccount("4444")
                    .debitAccount("1234")
                    .date(dateBuilder(1))
                    .build());
            transferRepository.save(Transfer.builder()
                    .enterprise(e3)
                    .amount(amount)
                    .creditAccount("4444")
                    .debitAccount("1234")
                    .date(dateBuilder(2))
                    .build());
            transferRepository.save(Transfer.builder()
                    .enterprise(e3)
                    .amount(amount)
                    .creditAccount("4444")
                    .debitAccount("1234")
                    .date(dateBuilder(2))
                    .build());
            transferRepository.save(Transfer.builder()
                    .enterprise(e4)
                    .amount(amount)
                    .creditAccount("4444")
                    .debitAccount("1234")
                    .date(dateBuilder(2))
                    .build());
            transferRepository.save(Transfer.builder()
                    .enterprise(e4)
                    .amount(amount)
                    .creditAccount("4444")
                    .debitAccount("1234")
                    .date(dateBuilder(2))
                    .build());
        }
    }

    private String dateBuilder(int range){
        return LocalDate.now()
                .minusMonths(range)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
