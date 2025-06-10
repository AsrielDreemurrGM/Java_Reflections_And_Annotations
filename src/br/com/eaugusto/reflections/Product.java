/**
 * @author Eduardo Augusto (https://github.com/AsrielDreemurrGM/)
 * @since Jun 10, 2025
 */

package br.com.eaugusto.reflections;

public class Product {

	private Long code;

	private String description;

	private Double price;

	public Product() {

	}

	public Long getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(Double value) {
		this.price = value;
	}
}
