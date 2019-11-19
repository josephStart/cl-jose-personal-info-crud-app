package com.example.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entity.PersonalInfo;

public interface PersonalInfoDao extends JpaRepository<PersonalInfo, Integer> {

}
