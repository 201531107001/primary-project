package com.me.teacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.teacher.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{

}
