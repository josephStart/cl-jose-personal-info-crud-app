package com.example.app.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dao.PersonalInfoDao;
import com.example.app.entity.PersonaInfo;

@RestController
@RequestMapping("/persona")
public class PersonaInfoRestController {
	
	@Autowired
	private PersonalInfoDao personalInfoDao;
	
	@GetMapping({"","/"})
	public ResponseEntity<List<PersonaInfo>> listPersonalInfo(){
		return ResponseEntity.ok(personalInfoDao.findAll());
	}
	
	@GetMapping("/{idpersona}")
	public ResponseEntity<PersonaInfo> findPersonalInfoById(@PathVariable Integer idpersona){
		Optional<PersonaInfo> personalInfoOp = personalInfoDao.findById(idpersona);
		return (personalInfoOp.isPresent() ? ResponseEntity.ok(personalInfoOp.get()) : ResponseEntity.noContent().build());
	}
	
	

}
