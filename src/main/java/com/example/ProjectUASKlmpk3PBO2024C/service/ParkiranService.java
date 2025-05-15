package com.example.ProjectUASKlmpk3PBO2024C.service;

import com.example.ProjectUASKlmpk3PBO2024C.entity.ParkiranEntity;
import com.example.ProjectUASKlmpk3PBO2024C.entity.UserEntity;
import com.example.ProjectUASKlmpk3PBO2024C.repository.ParkiranRepository;
import com.example.ProjectUASKlmpk3PBO2024C.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@Transactional
public class ParkiranService {
    @Autowired
    private ParkiranRepository parkiranRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ParkiranEntity> getAllParkiran() {
        return parkiranRepository.findAll();
    }

    public ParkiranEntity saveParkiran(ParkiranEntity parkiran) {
        if (parkiran.getUser() != null) {
            UserEntity user = parkiran.getUser();
            // Simpan user baru jika belum ada id
            if (user.getId() == null) {
                user = userRepository.save(user);
            }
            parkiran.setUser(user);
        }
        return parkiranRepository.save(parkiran);
    }

    public ParkiranEntity getParkiranById(Long id) {
        return parkiranRepository.findById(id).orElse(null);
    }

    public void deleteParkiran(Long id) {
        parkiranRepository.deleteById(id);
    }

    public Long getJumlahKendaraan() {
        return parkiranRepository.count();
    }
}
