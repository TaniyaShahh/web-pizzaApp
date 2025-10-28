package com.laziz.backend.repository;

import com.laziz.backend.entity.MenuSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuSizeRepository extends JpaRepository<MenuSize, String> {
}
