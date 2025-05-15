package com.example.ProjectUASKlmpk3PBO2024C.util;

import com.example.ProjectUASKlmpk3PBO2024C.entity.ParkiranEntity;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class SortingUtil {
    
    public List<ParkiranEntity> sortByTarif(List<ParkiranEntity> listParkir) {
        return listParkir.stream()
                .filter(p -> p.getTarifPerJam() != null)
                .sorted(Comparator.comparing(ParkiranEntity::getTarifPerJam, 
                        Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
    }

    public List<ParkiranEntity> sortByNama(List<ParkiranEntity> listParkir) {
        return listParkir.stream()
                .filter(p -> p.getUser() != null && p.getUser().getNama() != null)
                .sorted(Comparator.comparing(
                    p -> p.getUser().getNama().toLowerCase(),
                    Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)))
                .collect(Collectors.toList());
    }

    public List<ParkiranEntity> sortByWaktu(List<ParkiranEntity> listParkir) {
        return listParkir.stream()
                .filter(p -> p.getWaktuMasuk() != null)
                .sorted(Comparator.comparing(ParkiranEntity::getWaktuMasuk,
                        Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
    }
}
