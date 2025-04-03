package company.dto.request;

import java.util.regex.Pattern;

public record UpdateAccountDTORequest(
        String name,
        String surname,
        String username,
        String email,
        String phone,
        String password
) {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public UpdateAccountDTORequest {
        if (name == null || name.length() < 3) {
            throw new IllegalArgumentException("Name must be at least 3 characters");
        }
        if (surname == null || surname.length() < 3) {
            throw new IllegalArgumentException("Surname must be at least 3 characters");
        }
        if (username == null || username.length() < 5) {
            throw new IllegalArgumentException("Username must be at least 5 characters");
        }
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
    }
}