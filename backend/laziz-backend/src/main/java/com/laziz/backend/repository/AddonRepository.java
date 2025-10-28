package com.laziz.backend.repository;

import com.laziz.backend.entity.Addon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddonRepository extends JpaRepository<Addon, String> {
}
