package company.model;

import java.io.Serializable;

public record Account(
        Long id,
        String email,
        String name,
        String password,
        String phoneNumber,
        String surname,
        String username,
        Boolean isActive
) implements Serializable {

    private static final long serialVersionUID = 1L;

    public Account {
        if (email == null || username == null || password == null) {
            throw new IllegalArgumentException("Email, username, and password are required fields.");
        }
    }

    public Account withIsActive(Boolean newIsActive) {
        return new Account(id, email, name, password, phoneNumber, surname, username, newIsActive);
    }
}

