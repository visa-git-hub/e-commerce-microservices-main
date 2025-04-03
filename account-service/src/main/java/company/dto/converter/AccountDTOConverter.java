package company.dto.converter;

import company.dto.AccountDTO;
import company.model.Account;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AccountDTOConverter {

    public AccountDTO convert(Account account) {
        return new AccountDTO(
                account.id(),       // Direct field access without getters
                account.name(),
                account.surname(),
                account.username(),
                account.email(),
                account.phoneNumber(),
                account.isActive()
        );
    }

    public List<AccountDTO> convert(List<Account> accounts) {
        return accounts.stream()
                .map(this::convert)
                .toList();  // More efficient in Java 21
    }
}
