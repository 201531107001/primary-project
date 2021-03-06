package com.me.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.me.entity.Student;

import utils.TimeUtils;

@Repository
public class StudentDaoImpl{
	
	@Autowired
	JdbcOperations jdbcOperations;
	
	/**
	 * 根据id查询学生。并加入缓存
	 * @param id 
	 * @return
	 */
	@Cacheable(value="studentCache" , key="#root.args[0]")
	public Student getById(int id) {
		String sql = "select * from student where id = ?";
		
		System.out.println("查询id:"+id);
		return jdbcOperations.query(sql, new Object[] {id}, new ResultSetExtractor<Student>() {

			public Student extractData(ResultSet rSet) throws SQLException, DataAccessException {
				if(rSet.next()){
					try {
						return new Student(rSet.getInt(1), rSet.getString(2), rSet.getInt(3), TimeUtils.getNormalYMDTime(rSet.getDate(4).getTime()));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return null;
			}
			
		});
	}

	/**
	 * 储存或更新学生
	 * @param student
	 * @return
	 */
	@CachePut(value="studentCache",key="#result.id")
	public Student save(Student student) {
		Integer id = student.getId();
		if (id == null) {
			id = insertStudentAndReturnId(student);
			student.setId(id);
			return student;
		} else {
			jdbcOperations.update("update student set name=?, age=?, birth=? where id = ?",					
					student.getName(),
					student.getAge(),
					student.getBirth(),
					id);
		}
		return student;
	}
	
	/**
	 * 插入学生并返回id
	 * @param student
	 * @return
	 */
	private int insertStudentAndReturnId(Student student) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert((JdbcTemplate)jdbcOperations).withTableName("student");
		jdbcInsert.setGeneratedKeyName("id");
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("name", student.getName());
		args.put("age", student.getAge());
		args.put("birth", student.getBirth());
		int id = (int) jdbcInsert.executeAndReturnKey(args).longValue();
		return id;
	}

}
