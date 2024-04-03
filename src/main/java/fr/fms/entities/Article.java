package fr.fms.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.core.style.ToStringCreator;


@Entity
public class Article implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String brand;
	private String description;
	private double price;
	@ManyToOne
	private Category category;
	public Article() {   }
	
	public Article(Long id , String brand , String description , double price)
	{
		this.id = id;
		this.brand = brand;
		this.description = description;
		this.price = price;
	}

	public Article(String description, String brand, double price) 
	{
		this.description = description;
		this.brand = brand;
		this.price = price;
	}
	
	public Article(String description, String brand, double price, Category cat) 
	{
		this.description = description;
		this.brand = brand;
		this.price = price;
		this.category = cat;
	}

	public String toString()
	{
		return "article brand : " + this.brand + " ,  model : " +  this.description + " , prix : " + this.price;
	}
}