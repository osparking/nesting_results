package com.ezen.nesting_results;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CompanyMain {

	public static void main(String[] args) {
		try {
			CompanyMain main = new CompanyMain();
			/**
			 * 과 직원 목록 출력
			 */
			Dept dept = main.getEmployeeAndDeptData();
			List<Employee> empList = dept.getEmployee();
			System.out.println("과 이름: " + dept.getDeptName());
			for (Employee employee : empList) {
				System.out.println(employee.getEmpId() + "-" + employee.getEmpName() + "-" + employee.getSalary());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static SqlSession getSqlSession() throws Exception {
		String resource = "core-mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory.openSession();
	}

	private Dept getEmployeeAndDeptData() throws Exception {
		HashMap<String, Integer> inputMap = new HashMap<String, Integer>();
		inputMap.put("deptId", 1);
		return (Dept) getSqlSession().selectOne("getEmployeeAndDeptData", inputMap);
	}

}
