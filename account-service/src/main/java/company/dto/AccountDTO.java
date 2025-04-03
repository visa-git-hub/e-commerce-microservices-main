package company.dto;

public record AccountDTO(
        Long id,
        String name,
        String surname,
        String username,
        String email,
        String phoneNumber,
        Boolean isActive
) {
    public AccountDTO(Long id, String name, String surname, String username, String email, String phoneNumber) {
        this(id, name, surname, username, email, phoneNumber, false);
    }
}
