package com.example.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entity.PersonaInfo;

public interface PersonalInfoDao extends JpaRepository<PersonaInfo, Integer> {

}
