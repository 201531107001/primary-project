package com.me.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.me.config.DataConfig;
import com.me.entity.User;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DataConfig.class,loader=AnnotationConfigContextLoader.class)
public class UserRepositoryTest extends TestCase {

	@Autowired
	UserRepository userRepository;
	
	@Test
	public void testGetByName() {
		System.out.println(userRepository.getByName("gqm").getAge());
	}
	
	@Test
	public void testSave() {
		userRepository.save(new User("gql", 26));
	}

}
