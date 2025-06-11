package br.com.eaugusto.reflections.annotations.domain;

import java.util.Objects;

import br.com.eaugusto.reflections.annotations.annotation.KeyType;

/**
 * Represents a client (customer) with personal and contact information.
 * Implements Persistable to provide a unique identifier (CPF).
 * 
 * @author Eduardo Augusto (https://github.com/AsrielDreemurrGM/)
 * @since May 20, 2025
 */
public class Client implements Persistable {

	private String name;
	@KeyType("getCpf")
	private String cpf;
	private String phoneNumber;
	private String address;
	private String addressNumber;
	private String city;
	private String state;

	/**
	 * Constructs a Client instance with full details.
	 * 
	 * @param name          client's full name
	 * @param cpf           unique identifier (Brazilian CPF)
	 * @param phoneNumber   contact phone number
	 * @param address       street address
	 * @param addressNumber number of the address
	 * @param city          city of residence
	 * @param state         state of residence
	 */
	public Client(String name, String cpf, String phoneNumber, String address, String addressNumber, String city,
			String state) {
		this.name = name;
		this.cpf = cpf.trim();
		this.phoneNumber = phoneNumber.trim();
		this.address = address;
		this.addressNumber = addressNumber.trim();
		this.city = city;
		this.state = state;
	}

	// --- Getters ---
	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	// --- Setters ---
	public void setName(String name) {
		this.name = name;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Updates this client's fields with the data from another Client instance,
	 * except CPF, which uniquely identifies this client.
	 * 
	 * @param newInformations client object with updated data
	 */
	public void updateWith(Client newInformations) {
		this.setName(newInformations.getName());
		this.setPhoneNumber(newInformations.getPhoneNumber());
		this.setAddress(newInformations.getAddress());
		this.setAddressNumber(newInformations.getAddressNumber());
		this.setCity(newInformations.getCity());
		this.setState(newInformations.getState());
	}

	/**
	 * Equality is based solely on CPF since it uniquely identifies the client.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Client other = (Client) obj;
		return Objects.equals(cpf, other.cpf);
	}

	/**
	 * Returns a formatted string representing client's details.
	 */
	@Override
	public String toString() {
		return "Informações do Cliente: \n" + "Nome: " + name + "\nCPF: " + cpf + "\nNúmero de Telefone: " + phoneNumber
				+ "\nEndereço: " + address + "\nNúmero do Endereço: " + addressNumber + "\nCidade: " + city
				+ "\nEstado: " + state;
	}
}
