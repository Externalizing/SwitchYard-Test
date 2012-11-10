package com.example.switchyard.switchyard_example;

import org.switchyard.component.bean.Service;

@Service(HelloService.class)
public class HelloServiceBean implements HelloService {

	@Override
	public String sayHello(String helloString) {
		System.out.println("Hello: " + helloString);
        return "Hello " + helloString + "!"; 
	}

}
