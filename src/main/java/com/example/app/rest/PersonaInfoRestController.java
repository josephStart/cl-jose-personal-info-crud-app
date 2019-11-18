package com.example.app.rest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dao.PersonalInfoDao;
import com.example.app.entity.PersonaInfo;
import com.example.app.error.ErrorMessage;

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
	
	@PostMapping("/add-personal-info")
	public ResponseEntity<Object> addPersonalInfo(@RequestBody PersonaInfo pi){
		
		ResponseEntity<Object> responseEntity = null;
		
		try {
			if(pi != null) {
				if(pi.getId() != null) {
					responseEntity = new ResponseEntity<>(new ErrorMessage(new Date(), new Throwable("Please, don't send field 'id' on Body")), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
				}else {
					responseEntity = ResponseEntity.ok(personalInfoDao.save(pi));
				}
			}
		}catch(Exception ex) {
			responseEntity = new ResponseEntity<>(new ErrorMessage(new Date(), ex), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return responseEntity;
	}

}
