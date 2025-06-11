package br.com.eaugusto.reflections.annotations.domain;

/**
 * Represents a product with identifying code, description, and pricing details.
 * Implements Persistable to provide a unique identifier (code).
 * 
 * @author Eduardo Augusto (https://github.com/AsrielDreemurrGM/)
 * @since June 02, 2025
 */
public class Product implements Persistable {

	private String code;
	private String name;
	private String description;
	private double value;
	private String brand;
	
	/**
	 * Constructs a Product instance with all attributes.
	 * 
	 * @param name product name
	 * @param code unique product code
	 * @param description product description
	 * @param value product price/value
	 * @param brand product brand
	 */
	public Product(String name, String code, String description, double value, String brand) {
		this.name = name;
		this.code = code;
		this.description = description;
		this.value = value;
		this.brand = brand;
	}
	
	// --- Getters ---
	public String getDescription() {
		return description;
	}

	public double getValue() {
		return value;
	}

	public String getBrand() {
		return brand;
	}

	// --- Setters ---
	public void setDescription(String description) {
		this.description = description;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns a formatted string representing product details.
	 */
	@Override
	public String toString() {
	    return "Informações do Produto:\n"
	        + "Nome: " + name + "\n"
	        + "Código: " + code + "\n"
	        + "Descrição: " + description + "\n"
	        + "Valor: R$ " + String.format("%.2f", value) + "\n"
	        + "Marca: " + brand;
	}

	/**
	 * Returns the unique product code as the persistence identifier.
	 */
	@Override
	public String getCodeOrCPF() {
		return this.code;
	}
}
