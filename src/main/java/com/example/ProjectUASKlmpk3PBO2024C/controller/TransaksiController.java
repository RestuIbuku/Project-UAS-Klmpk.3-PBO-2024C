package com.example.ProjectUASKlmpk3PBO2024C.controller;

import com.example.ProjectUASKlmpk3PBO2024C.service.TransaksiService;
import com.example.ProjectUASKlmpk3PBO2024C.entity.ParkiranEntity;
import com.example.ProjectUASKlmpk3PBO2024C.util.SearchingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

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
