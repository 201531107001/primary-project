package com.me.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.me.config.DataConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DataConfig.class,loader=AnnotationConfigContextLoader.class)
public class ShiroRepositoryTest {
	
	@Autowired
	ShiroRepository userRepository;
	
	@Test
	public void testGetPasswordByUsername() {
		System.out.println(userRepository.getPasswordByUsername("gqm"));
	}

	@Test
	public void testGetPermissionByUserName() {
		System.out.println(userRepository.getPermissionByUserName("gqm"));
	}

}
