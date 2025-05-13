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