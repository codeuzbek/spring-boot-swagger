package dasturlash.uz.service;

import dasturlash.uz.dto.auth.AuthDTO;
import dasturlash.uz.dto.auth.RegistrationDTO;
import dasturlash.uz.dto.profile.ProfileDTO;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.exps.AppBadRequestException;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.util.JwtUtil;
import dasturlash.uz.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;

    public String registration(RegistrationDTO dto) {
        // check email exists
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(dto.getEmail());
        if (optional.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5Util.md5(dto.getPassword()));
        entity.setSurname(dto.getSurname());
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setVisible(Boolean.TRUE);
        entity.setRole(ProfileRole.ROLE_USER);
        entity.setCreatedDate(LocalDateTime.now());
        profileRepository.save(entity);
        return "Registration was successfully";
    }

    public ProfileDTO login(AuthDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(dto.getEmail());
        if (optional.isEmpty()) {
            throw new AppBadRequestException(" Email or Password wrong");
        }
        ProfileEntity entity = optional.get();
        if (!entity.getPassword().equals(MD5Util.md5(dto.getPassword()))) {
            throw new AppBadRequestException(" Email or Password wrong");
        }
        if (!entity.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new AppBadRequestException(" Email or Password wrong");
        }
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setName(entity.getName());
        profileDTO.setSurname(entity.getSurname());
        profileDTO.setEmail(entity.getEmail());
        profileDTO.setRole(entity.getRole());
        profileDTO.setJwtToken(JwtUtil.encode(entity.getEmail(), entity.getRole().toString()));
        return profileDTO;
    }
}
