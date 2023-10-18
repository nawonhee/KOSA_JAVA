package com.my.di.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component(value="b")
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class B {
	private int no;
	private A a;
}
