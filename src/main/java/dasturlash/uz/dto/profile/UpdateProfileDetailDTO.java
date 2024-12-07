package dasturlash.uz.dto.profile;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileDetailDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    private String photoId;

}
