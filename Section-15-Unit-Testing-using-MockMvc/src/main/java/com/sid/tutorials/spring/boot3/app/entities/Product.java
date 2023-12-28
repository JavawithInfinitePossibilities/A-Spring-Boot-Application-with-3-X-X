/**
 * 
 */
package com.sid.tutorials.spring.boot3.app.entities;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author kunmu
 *
 */
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "product_name")
	private String name;
	@Column(name = "product_description")
	private String description;
	@Column(name = "product_price")
	private double price;

}
