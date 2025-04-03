package company.service;

import company.dto.AccountDTO;
import company.dto.converter.AccountDTOConverter;
import company.dto.request.CreateAccountDTORequest;
import company.dto.request.UpdateAccountDTORequest;
import company.exception.AccountExistException;
import company.exception.AccountNotFoundException;
import company.model.Account;
import company.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class AccountService {

    private final AccountRepository accountRepository;
    private final AccountDTOConverter accountDTOConverter;

    public AccountService(AccountRepository accountRepository, AccountDTOConverter accountDTOConverter) {
        this.accountRepository = accountRepository;
        this.accountDTOConverter = accountDTOConverter;
    }

    public AccountDTO createUser(CreateAccountDTORequest request) {
        validateAccount(request.email(), request.username(), request.phone());

        var account = new Account(null, request.email(), request.name(), request.password(),
                request.phone(), request.surname(), request.username(), false);

        return accountDTOConverter.convert(accountRepository.save(account));
    }

    public List<AccountDTO> getAllAccounts() {
        return accountDTOConverter.convert(accountRepository.findAll());
    }

    public AccountDTO updateAccount(Long id, UpdateAccountDTORequest request) {
        var existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("User couldn't be found by id: " + id));

        validateAccount(request.email(), request.username(), request.phone());

        var updatedAccount = new Account(id, request.email(), request.name(), request.password(),
                request.phone(), request.surname(), request.username(), existingAccount.isActive());

        return accountDTOConverter.convert(accountRepository.save(updatedAccount));
    }

    public AccountDTO getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(accountDTOConverter::convert)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));
    }

    public AccountDTO findAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username)
                .map(accountDTOConverter::convert)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with username: " + username));
    }

    private void validateAccount(String email, String username, String phone) {
        if (accountRepository.existsByUsername(username)) {
            throw new AccountExistException("Account exists with username: " + username);
        }
        if (accountRepository.existsByEmail(email)) {
            throw new AccountExistException("Account exists with email: " + email);
        }
        if (accountRepository.existsByPhoneNumber(phone)) {
            throw new AccountExistException("Account exists with phone number: " + phone);
        }
    }

    public void deleteAccount(Long userId) {
        accountRepository.deleteById(userId);
    }

    public void deactivateAccount(Long userId) {
        accountRepository.findById(userId)
                .ifPresentOrElse(account -> accountRepository.save(account.withIsActive(false)),
                        () -> { throw new AccountNotFoundException("User not found by id: " + userId); });
    }

    public void activateAccount(Long userId) {
        accountRepository.findById(userId)
                .ifPresentOrElse(account -> accountRepository.save(account.withIsActive(true)),
                        () -> { throw new AccountNotFoundException("User not found by id: " + userId); });
    }
}
