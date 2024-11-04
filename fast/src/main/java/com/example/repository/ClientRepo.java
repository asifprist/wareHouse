package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.ClientDetail;

public interface ClientRepo extends JpaRepository<ClientDetail, String>{

}
