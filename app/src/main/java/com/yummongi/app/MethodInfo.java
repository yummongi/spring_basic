package com.yummongi.app;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;

public class MethodInfo {
	public static void main(String[] args) throws Exception{
		
		// 1. YoilTeller Ŭ������ ��ü�� ����
		Class clazz = Class.forName("com.yummongi.app.YoilTellerMVC");
		Object obj = clazz.newInstance();
		
		// 2. ��� �޼ҵ� ������ �����ͼ� �迭�� ����
		Method[] methodArr = clazz.getDeclaredMethods();
		
		// 3. �ݺ������� �޼ҵ带 �ϳ��� ���
	
		for(Method m : methodArr) {
			String name = m.getName(); //�޼ҵ� �̸�
			Parameter[] paramArr = m.getParameters(); //�ŰԺ��� ���
//			Class[] paramTypeArr = m.getParameterTypes();
			Class returnType = m.getReturnType(); //�޼ҵ��� ��ȯ Ÿ��
			
			StringJoiner paramList = new StringJoiner(", ", "(", ")"); //������ , ���λ� ( ���̻� )
			
			for(Parameter param : paramArr) {
				String paramName = param.getName();
				Class  paramType = param.getType();
				
				paramList.add(paramType.getName() + " " + paramName);
			}
			
			System.out.printf("%s %s%s%n", returnType.getName(), name, paramList);
		}
	} // main
}