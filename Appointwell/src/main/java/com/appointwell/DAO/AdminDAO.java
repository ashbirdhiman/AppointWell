package com.appointwell.DAO;

import com.appointwell.POJO.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDAO extends JpaRepository<Admin, Integer> {
    Admin findByEmail(String email);
}
