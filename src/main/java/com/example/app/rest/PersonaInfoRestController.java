package com.example.app.rest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dao.PersonalInfoDao;
import com.example.app.entity.PersonalInfo;
import com.example.app.error.ErrorMessage;

@RestController
@RequestMapping("/persona")
public class PersonaInfoRestController {
	
	@Autowired
	private PersonalInfoDao personalInfoDao;
	
	@GetMapping({"","/"})
	public ResponseEntity<List<PersonalInfo>> listPersonalInfo(){
		return ResponseEntity.ok(personalInfoDao.findAll());
	}
	
	@GetMapping("/{idpersona}")
	public ResponseEntity<PersonalInfo> findPersonalInfoById(@PathVariable Integer idpersona){
		Optional<PersonalInfo> personalInfoOp = personalInfoDao.findById(idpersona);
		return (personalInfoOp.isPresent() ? ResponseEntity.ok(personalInfoOp.get()) : ResponseEntity.noContent().build());
	}
	
	@PostMapping("/add-personal-info")
	public ResponseEntity<Object> addPersonalInfo(@RequestBody PersonalInfo pi){
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
	
	@DeleteMapping("/delete-personal-info/{idpersona}")
	public ResponseEntity<Object> deletePersonalInfo(@PathVariable Integer idpersona){
		ResponseEntity<Object> responseEntity = null;
		try {
			personalInfoDao.deleteById(idpersona);
			responseEntity = new ResponseEntity<Object>(HttpStatus.OK);
		}catch(Exception ex) {
			responseEntity = new ResponseEntity<>(new ErrorMessage(new Date(), ex), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@PutMapping("/update-personal-info")
	public ResponseEntity<PersonalInfo> updatePersonalInfo(@RequestBody PersonalInfo pi){
		Optional<PersonalInfo> updatePersonalInfo = personalInfoDao.findById(pi.getId());
		return (updatePersonalInfo.isPresent() ? ResponseEntity.ok(updatePersonalInfoAux(updatePersonalInfo, pi)) : ResponseEntity.notFound().build());
	}
	
	private PersonalInfo updatePersonalInfoAux(Optional<PersonalInfo> UPInf, PersonalInfo pi){
		PersonalInfo updatePi = UPInf.get();
		updatePi.setName(pi.getName());
		updatePi.setLastName(pi.getLastName());
		updatePi.setAddres(pi.getAddres());
		updatePi.setPhoneNumber(pi.getPhoneNumber());
		updatePi.setHairColor(pi.getHairColour());
		personalInfoDao.save(updatePi);
		return updatePi;
	}

}
