package dasturlash.uz.service;

import dasturlash.uz.dto.profile.ProfileCreationDTO;
import dasturlash.uz.dto.profile.ProfileDTO;
import dasturlash.uz.dto.profile.UpdateProfileDetailDTO;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.exps.AppBadRequestException;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.util.MD5Util;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    protected ProfileRepository profileRepository;

    public void initProfile() {
        String email = "adminjon@gmail.com";
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(email);
        if (optional.isPresent()) {
            return;
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName("Adminjon");
        entity.setSurname("Aminnov");
        entity.setEmail(email);
        entity.setPassword(MD5Util.md5("12345"));
        entity.setRole(ProfileRole.ROLE_ADMIN);
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setVisible(Boolean.TRUE);
        entity.setCreatedDate(LocalDateTime.now());
        profileRepository.save(entity); // saving into database
    }
}


