package com.example.ProjectUASKlmpk3PBO2024C.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ProjectUASKlmpk3PBO2024C.entity.ParkiranEntity;
import com.example.ProjectUASKlmpk3PBO2024C.repository.ParkiranRepository;
@Service
public class TransaksiService {
    @Autowired
    private ParkiranRepository parkiranRepository;

    public List<ParkiranEntity> getAllTransaksi() {
        return parkiranRepository.findAll().stream()
                .filter(p -> p.getStatusBayar() != null && p.getStatusBayar())
                .filter(p -> p.getTotalBayar() != null)
                .collect(Collectors.toList());
    }

    public int hitungTotalPendapatan() {
        return getAllTransaksi().stream()
                .filter(p -> p.getTotalBayar() != null)
                .mapToInt(ParkiranEntity::getTotalBayar)
                .sum();
    }

    public long getJumlahTransaksi() {
        return getAllTransaksi().size();
    }

    public double getRataRataPendapatan() {
        List<ParkiranEntity> transaksi = getAllTransaksi();
        if (transaksi.isEmpty()) {
            return 0;
        }
        return transaksi.stream()
                .mapToInt(ParkiranEntity::getTotalBayar)
                .average()
                .orElse(0);
    }

    public void prosesPembayaran(Long parkirId) {
        ParkiranEntity parkir = parkiranRepository.findById(parkirId)
            .orElseThrow(() -> new RuntimeException("Parkir tidak ditemukan"));
            
        parkir.setWaktuKeluar(LocalDateTime.now());
        parkir.setStatusBayar(true);
        parkir.setTotalBayar(hitungTotalBayar(parkir));
        
        parkiranRepository.save(parkir);
    }
    
    private int hitungTotalBayar(ParkiranEntity parkir) {
        return parkir.getLamaParkir() * parkir.getTarifPerJam();
    }

    // Mendapatkan transaksi dengan pembayaran tertinggi
    public ParkiranEntity getTransaksiTertinggi() {
        List<ParkiranEntity> semuaParkir = getAllTransaksi();
        return semuaParkir.stream()
                .max((a, b) -> a.getTotalBayar().compareTo(b.getTotalBayar()))
                .orElse(null);
    }

    // Mendapatkan transaksi dengan pembayaran terendah
    public ParkiranEntity getTransaksiTerendah() {
        List<ParkiranEntity> semuaParkir = getAllTransaksi();
        return semuaParkir.stream()
                .min((a, b) -> a.getTotalBayar().compareTo(b.getTotalBayar()))
                .orElse(null);
    }

    // Menghitung jumlah transaksi berdasarkan jenis kendaraan
    public long getJumlahTransaksiByJenisKendaraan(String jenisKendaraan) {
        List<ParkiranEntity> semuaParkir = getAllTransaksi();
        return semuaParkir.stream()
                .filter(p -> p.getJenisKendaraan().equals(jenisKendaraan))
                .count();
    }

    public Double getPendapatanHariIni() {
        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        
        List<ParkiranEntity> parkiranHariIni = parkiranRepository.findByWaktuKeluarBetweenAndStatusBayarTrue(startOfDay, endOfDay);
        
        return parkiranHariIni.stream()
                .mapToDouble(ParkiranEntity::getTotalBayar)
                .sum();
    }

    public Long getTotalTransaksi() {
        return parkiranRepository.countByStatusBayarTrue();
    }
}
