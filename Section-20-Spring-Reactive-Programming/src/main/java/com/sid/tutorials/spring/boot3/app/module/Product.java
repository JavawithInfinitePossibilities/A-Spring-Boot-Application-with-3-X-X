/**
 * 
 */
package com.sid.tutorials.spring.boot3.app.module;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * @author kunmu
 *
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String description;
	private double price;

}
