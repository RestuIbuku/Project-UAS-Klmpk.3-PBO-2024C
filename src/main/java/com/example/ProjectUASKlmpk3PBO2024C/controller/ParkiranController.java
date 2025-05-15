package com.example.ProjectUASKlmpk3PBO2024C.controller;

import com.example.ProjectUASKlmpk3PBO2024C.entity.ParkiranEntity;
import com.example.ProjectUASKlmpk3PBO2024C.service.ParkiranService;
import com.example.ProjectUASKlmpk3PBO2024C.util.SearchingUtil;
import com.example.ProjectUASKlmpk3PBO2024C.util.SortingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/parkiran")
public class ParkiranController {
    @Autowired
    private ParkiranService parkiranService;
    
    @Autowired
    private SearchingUtil searchingUtil;

    @Autowired
    private SortingUtil sortingUtil;

    @GetMapping
    public String listParkiran(Model model, 
                             @RequestParam(required = false) String search,
                             @RequestParam(required = false) String sortBy) {
        List<ParkiranEntity> parkiranList = parkiranService.getAllParkiran();
        
        // Jika ada pencarian
        if (search != null && !search.isEmpty()) {
            if (search.matches(".*\\d+.*")) { // Jika mengandung angka, cari berdasarkan plat
                parkiranList = searchingUtil.searchByPlat(parkiranList, search);
            } else { // Jika tidak mengandung angka, cari berdasarkan nama
                parkiranList = searchingUtil.searchByNama(parkiranList, search);
            }
        }

         if (sortBy != null) {
        switch (sortBy.toLowerCase()) {
            case "nama":
                parkiranList = sortingUtil.sortByNama(parkiranList);
                break;
            case "tarif":
                parkiranList = sortingUtil.sortByTarif(parkiranList);
                break;
            case "waktu":
                parkiranList = sortingUtil.sortByWaktu(parkiranList);
                break;
        }
    }

        model.addAttribute("parkiranList", parkiranList);
        model.addAttribute("currentSort", sortBy);
        return "parkiran/daftarparkir";
    }


    @GetMapping("/tambah")
    public String showFormTambah(Model model) {
        model.addAttribute("parkiran", new ParkiranEntity());
        return "parkiran/form";
    }

    @PostMapping("/tambah")
    public String tambahParkiran(@ModelAttribute ParkiranEntity parkiran) {
        parkiranService.saveParkiran(parkiran);
        return "redirect:/parkiran";
    }

    @PostMapping("/simpan")
    public String simpanParkiran(@ModelAttribute ParkiranEntity parkiran) {
        try {
            // Set waktu masuk dan status bayar jika data baru
            if (parkiran.getId() == null) {
                parkiran.setWaktuMasuk(LocalDateTime.now());
                parkiran.setStatusBayar(false);
                switch (parkiran.getJenisKendaraan().toUpperCase()) {
                    case "MOTOR": parkiran.setTarifPerJam(2000); break;
                    case "MOBIL": parkiran.setTarifPerJam(5000); break;
                    case "TRUK": parkiran.setTarifPerJam(10000); break;
                }
            }
            // Pastikan user tidak null
            if (parkiran.getUser() == null) {
                throw new IllegalArgumentException("User tidak boleh kosong!");
            }
            parkiranService.saveParkiran(parkiran);
            return "redirect:/parkiran";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/parkiran?error=true";
        }
    }

    @GetMapping("/edit/{id}")
    public String showFormEdit(@PathVariable Long id, Model model) {
        ParkiranEntity parkiran = parkiranService.getParkiranById(id);
        model.addAttribute("parkiran", parkiran);
        return "parkiran/form";
    }

    @GetMapping("/hapus/{id}")
    public String hapusParkiran(@PathVariable Long id) {
        parkiranService.deleteParkiran(id);
        return "redirect:/parkiran";
    }

    @GetMapping("/bayar/{id}")
    public String showBayar(@PathVariable Long id, Model model) {
        ParkiranEntity parkiran = parkiranService.getParkiranById(id);
        
        // Set waktu keluar dan hitung biaya jika belum dibayar
        if (!parkiran.getStatusBayar()) {
            LocalDateTime waktuKeluar = LocalDateTime.now();
            parkiran.setWaktuKeluar(waktuKeluar);
            
            // Hitung lama parkir dalam jam
            long hours = ChronoUnit.HOURS.between(parkiran.getWaktuMasuk(), waktuKeluar);
            int lamaParkir = (int) Math.max(1, hours); // minimal 1 jam
            parkiran.setLamaParkir(lamaParkir);
            
            // Hitung total bayar
            int totalBayar = lamaParkir * parkiran.getTarifPerJam();
            parkiran.setTotalBayar(totalBayar);
            
            // Simpan perubahan
            parkiranService.saveParkiran(parkiran);
        }
        
        model.addAttribute("parkiran", parkiran);
        return "parkiran/bayar";
    }

    @PostMapping("/bayar/{id}/process")
    public String processBayar(@PathVariable Long id) {
        ParkiranEntity parkiran = parkiranService.getParkiranById(id);
        if (!parkiran.getStatusBayar()) {
            parkiran.setStatusBayar(true);
            // Pastikan waktu keluar dan total bayar sudah terisi
            if (parkiran.getWaktuKeluar() == null) {
                parkiran.setWaktuKeluar(LocalDateTime.now());
            }
            parkiranService.saveParkiran(parkiran);
        }
        return "redirect:/parkiran/bayar/" + id;
    }

    @GetMapping("/struk/{id}")
    public String showStruk(@PathVariable Long id, Model model) {
        ParkiranEntity parkiran = parkiranService.getParkiranById(id);
        model.addAttribute("parkiran", parkiran);
        return "parkiran/bayar";
    }
}
