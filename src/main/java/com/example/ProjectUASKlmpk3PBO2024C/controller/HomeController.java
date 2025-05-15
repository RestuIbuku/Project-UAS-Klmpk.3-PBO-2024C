package com.example.ProjectUASKlmpk3PBO2024C.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.ProjectUASKlmpk3PBO2024C.service.ParkiranService;
import com.example.ProjectUASKlmpk3PBO2024C.service.TransaksiService;

@Controller
public class HomeController {
    
    @Autowired
    private ParkiranService parkiranService;
    
    @Autowired
    private TransaksiService transaksiService;
    
    @GetMapping("/")
    public String home(Model model) {
        // Menambahkan data untuk dashboard dengan penanganan null
        model.addAttribute("totalKendaraan", parkiranService.getJumlahKendaraan());
        model.addAttribute("pendapatanHariIni", transaksiService.getPendapatanHariIni() != null ? 
            String.format("%,.0f", transaksiService.getPendapatanHariIni()) : "0");
        model.addAttribute("totalTransaksi", transaksiService.getTotalTransaksi());
        
        return "home";
    }
}
