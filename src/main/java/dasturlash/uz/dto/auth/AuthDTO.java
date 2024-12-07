package dasturlash.uz.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
