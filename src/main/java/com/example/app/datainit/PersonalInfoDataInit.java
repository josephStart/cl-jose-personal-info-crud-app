package com.example.app.datainit;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.app.constants.PersonalInfoConstants;
import com.example.app.dao.PersonalInfoDao;
import com.example.app.entity.PersonalInfo;

@Component
public class PersonalInfoDataInit implements ApplicationRunner{
	
	@Autowired
	private PersonalInfoDao personalInfoDao;
	
	public void run(ApplicationArguments args) throws NoSuchAlgorithmException, NoSuchProviderException {
		SecureRandom sr = SecureRandom.getInstance(PersonalInfoConstants.RANDOM_ALGORITHM, PersonalInfoConstants.RANDOM_PROVIDER);
		if(personalInfoDao.count() == 0) {
			for(int t=1; t<=15; t++) {
				PersonalInfo pi = new PersonalInfo();
				pi.setName("name".concat(String.valueOf(t)));
				pi.setLastName("lastName".concat(String.valueOf(t)));
				pi.setAddres("addres".concat(String.valueOf(t)));
				pi.setPhoneNumber(sr.nextInt(Integer.MAX_VALUE));
				pi.setHairColor("haircolour".concat(String.valueOf(t)));
				personalInfoDao.save(pi);
			}
		}
	}

}
