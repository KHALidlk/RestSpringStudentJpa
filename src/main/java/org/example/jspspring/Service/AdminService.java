package org.example.jspspring.Service;

import org.example.jspspring.Model.Admin;
import org.example.jspspring.Repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdminService {
    private final AdminRepo adminRepo;
    @Autowired
    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }
    public void save(Admin admin) {
        adminRepo.save(admin);
    }
    public void delete(Admin admin) {
        adminRepo.delete(admin);
    }
public Admin findById(int id) {
        return adminRepo.findById(id);
    }
    public Admin findByName(String name) {
        return adminRepo.findByName(name).orElse(null);
    }
    public List<Admin> findAll() {
        return adminRepo.findAll();
    }


}
