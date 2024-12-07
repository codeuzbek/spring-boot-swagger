package dasturlash.uz.controller;

import dasturlash.uz.dto.auth.AuthDTO;
import dasturlash.uz.dto.auth.RegistrationDTO;
import dasturlash.uz.dto.profile.ProfileDTO;
import dasturlash.uz.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth APIs")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    @Operation(summary = "Registration", description = "This api used for registration new users")
    public ResponseEntity<String> registration(@RequestBody RegistrationDTO dto) {
        return ResponseEntity.ok(authService.registration(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "Authorization", description = "Api used for Authorization and Authentication")
    public ResponseEntity<ProfileDTO> login(@RequestBody @Valid AuthDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}
