package com.me.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.me.moredatabase.MoreDatabaseApplication;
import com.me.teacher.repository.TeacherRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MoreDatabaseApplication.class)
public class TeacherRepositoryTest {
	
	@Autowired
	TeacherRepository teacherRepository;

	@Test
	public void testFindAll() {
		System.out.println(teacherRepository.findAll());
	}

	@Test
	public void testFindAllSort() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllByIdIterableOfID() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveAllIterableOfS() {
		fail("Not yet implemented");
	}

	@Test
	public void testFlush() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveAndFlush() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteInBatch() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAllInBatch() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOne() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllExampleOfS() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllExampleOfSSort() {
		fail("Not yet implemented");
	}

}
