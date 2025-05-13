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