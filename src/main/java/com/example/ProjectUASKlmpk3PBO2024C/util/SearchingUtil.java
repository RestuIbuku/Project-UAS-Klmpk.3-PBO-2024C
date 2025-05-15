package com.example.ProjectUASKlmpk3PBO2024C.util;

import com.example.ProjectUASKlmpk3PBO2024C.entity.ParkiranEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class SearchingUtil {
    
    public List<ParkiranEntity> searchByPlat(List<ParkiranEntity> listParkir, String plat) {
        if (plat == null || plat.isEmpty()) {
            return listParkir;
        }
        String searchLower = plat.toLowerCase();
        return listParkir.stream()
                .filter(p -> p.getPlatNomor() != null &&
                           p.getPlatNomor().toLowerCase().contains(searchLower))
                .collect(Collectors.toList());
    }

    public List<ParkiranEntity> searchByNama(List<ParkiranEntity> listParkir, String nama) {
        String searchLower = nama.toLowerCase();
        return listParkir.stream()
                .filter(p -> p.getUser() != null && 
                           p.getUser().getNama() != null &&
                           p.getUser().getNama().toLowerCase().contains(searchLower))
                .collect(Collectors.toList());
    }

    public List<ParkiranEntity> searchTransaksi(List<ParkiranEntity> transaksiList, String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return transaksiList;
        }

        String searchLower = keyword.toLowerCase();
        return transaksiList.stream()
                .filter(t -> 
                    (t.getPlatNomor() != null && 
                     t.getPlatNomor().toLowerCase().contains(searchLower)) ||
                    (t.getUser() != null && 
                     t.getUser().getNama() != null && 
                     t.getUser().getNama().toLowerCase().contains(searchLower))
                )
                .collect(Collectors.toList());
    }
}
