package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.FileDocument;
import com.example.entity.Leads;
@Repository
public interface LeadRepository extends JpaRepository<Leads, String> {

	public Leads save(FileDocument updatefile);

}
