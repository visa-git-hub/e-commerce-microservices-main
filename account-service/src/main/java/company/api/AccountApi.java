package company.api;

import company.dto.AccountDTO;
import company.dto.request.CreateAccountDTORequest;
import company.dto.request.UpdateAccountDTORequest;
import company.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/account")
public class AccountApi {

    private final AccountService accountService;

    public AccountApi(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/add")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody CreateAccountDTORequest request) {
        validateCreateAccount(request);
        return ResponseEntity.ok(accountService.createUser(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountDTO> updateAccount(
            @PathVariable Long id, @RequestBody UpdateAccountDTORequest request) {
        validateUpdateAccount(request);
        return ResponseEntity.ok(accountService.updateAccount(id, request));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<AccountDTO>> getAllAccount() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/getuser/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.getAccountById(accountId));
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<AccountDTO> getAccountByUsername(@PathVariable String username) {
        return ResponseEntity.ok(accountService.findAccountByUsername(username));
    }

    @DeleteMapping("/deleteaccount/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/deactiveaccount/{id}")
    public ResponseEntity<Void> deactivateAccount(@PathVariable Long accountId) {
        accountService.deactivateAccount(accountId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/activeaccount/{id}")
    public ResponseEntity<Void> activateAccount(@PathVariable Long id) {
        accountService.activateAccount(id);
        return ResponseEntity.ok().build();
    }

    private void validateCreateAccount(CreateAccountDTORequest request) {
        if (request.name() == null || request.name().length() < 3) {
            throw new IllegalArgumentException("Name must be at least 3 characters");
        }
        if (request.surname() == null || request.surname().length() < 3) {
            throw new IllegalArgumentException("Surname must be at least 3 characters");
        }
        if (request.username() == null || request.username().length() < 5) {
            throw new IllegalArgumentException("Username must be at least 5 characters");
        }
        if (request.email() == null || !request.email().contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (request.phone() == null || request.phone().isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        if (request.password() == null || request.password().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
    }

    private void validateUpdateAccount(UpdateAccountDTORequest request) {
        if (request.name() != null && request.name().length() < 3) {
            throw new IllegalArgumentException("Name must be at least 3 characters");
        }
        if (request.surname() != null && request.surname().length() < 3) {
            throw new IllegalArgumentException("Surname must be at least 3 characters");
        }
        if (request.username() != null && request.username().length() < 5) {
            throw new IllegalArgumentException("Username must be at least 5 characters");
        }
        if (request.email() != null && !request.email().contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (request.password() != null && request.password().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
    }
}
