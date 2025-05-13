package org.example.jspspring.Controller;

import org.example.jspspring.Model.Admin;
import org.example.jspspring.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String getAllAdmins(Model model) {
        List<Admin> admins = adminService.findAll();
        model.addAttribute("admins", admins);
        return "admin/list";
    }

    @GetMapping("/{id}")
    public String getAdminById(@PathVariable int id, Model model) {
        Admin admin = adminService.findById(id);
        model.addAttribute("admin", admin);
        return "admin/detail";
    }

    @GetMapping("/name/{name}")
    public String getAdminByName(@PathVariable String name, Model model) {
        Admin admin = adminService.findByName(name);
        model.addAttribute("admin", admin);
        return "admin/detail";
    }

    @GetMapping("/create")
    public String createAdminForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/form";
    }

    @PostMapping("/save")
    public String saveAdmin(@ModelAttribute Admin admin) {
        adminService.save(admin);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable int id) {
        Admin admin = adminService.findById(id);
        if (admin != null) {
            adminService.delete(admin);
        }
        return "redirect:/admin";
    }
}