package com.me.swagger.demo.service;

import com.me.swagger.demo.entity.Person;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonService {
	Map<Integer,Person> map = new HashMap();

	public Person findById(Integer id){
		return map.get(id);
	}

	public List<Person> selectAll(){
		List<Person> list = new ArrayList<>();

		for (Map.Entry<Integer,Person> entry:map.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}
}
