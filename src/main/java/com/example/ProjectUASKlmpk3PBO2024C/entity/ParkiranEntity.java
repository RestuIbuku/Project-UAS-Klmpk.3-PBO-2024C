package com.example.ProjectUASKlmpk3PBO2024C.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "parkiran")
public class ParkiranEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    
    private String jenisKendaraan;
    private String platNomor;
    private LocalDateTime waktuMasuk;
    private LocalDateTime waktuKeluar;
    private Integer lamaParkir;
    private Integer tarifPerJam;
    private Integer totalBayar;
    private Boolean statusBayar;

    // Getter dan Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public LocalDateTime getWaktuMasuk() {
        return waktuMasuk;
    }

    public void setWaktuMasuk(LocalDateTime waktuMasuk) {
        this.waktuMasuk = waktuMasuk;
    }

    public LocalDateTime getWaktuKeluar() {
        return waktuKeluar;
    }

    public void setWaktuKeluar(LocalDateTime waktuKeluar) {
        this.waktuKeluar = waktuKeluar;
    }

    public Integer getLamaParkir() {
        return lamaParkir;
    }

    public void setLamaParkir(Integer lamaParkir) {
        this.lamaParkir = lamaParkir;
    }

    public Integer getTarifPerJam() {
        return tarifPerJam;
    }

    public void setTarifPerJam(Integer tarifPerJam) {
        this.tarifPerJam = tarifPerJam;
    }

    public Integer getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(Integer totalBayar) {
        this.totalBayar = totalBayar;
    }

    public Boolean getStatusBayar() {
        return statusBayar;
    }

    public void setStatusBayar(Boolean statusBayar) {
        this.statusBayar = statusBayar;
    }
}
