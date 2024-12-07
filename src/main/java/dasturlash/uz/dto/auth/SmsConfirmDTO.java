package dasturlash.uz.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsConfirmDTO {
    private String phone;
    private String code;
}
