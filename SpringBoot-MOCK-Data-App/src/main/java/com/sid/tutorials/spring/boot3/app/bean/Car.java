package com.sid.tutorials.spring.boot3.app.bean;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Car  {
	private Integer id;
	private String make;
	private String model;
	private String color;
	private Integer year;
	private Double price;
}
