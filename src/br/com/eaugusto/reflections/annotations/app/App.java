package br.com.eaugusto.reflections.annotations.app;

import javax.swing.JOptionPane;

import br.com.eaugusto.reflections.annotations.dao.ClientMapDAO;
import br.com.eaugusto.reflections.annotations.dao.IClientDAO;
import br.com.eaugusto.reflections.annotations.dao.IProductDAO;
import br.com.eaugusto.reflections.annotations.dao.ProductMapDAO;
import br.com.eaugusto.reflections.annotations.domain.Client;
import br.com.eaugusto.reflections.annotations.domain.Product;
import br.com.eaugusto.reflections.annotations.ui.MenuHelper;
import br.com.eaugusto.reflections.annotations.ui.PromptHelper;

/**
 * Main application class for managing Clients and Products.
 * <p>
 * This application provides a simple CRUD interface (using JOptionPane) to
 * register, search, delete, and modify Clients and Products in memory, using
 * either a Map-based DAO or Set-based DAO for Clients, and Map-based DAO for
 * Products.
 * <p>
 * The program allows the user to choose whether to manage Clients or Products,
 * and presents a simple dashboard to perform the available operations.
 *
 * <h2>Features:</h2>
 * <ul>
 * <li>Switch between using {@code ClientMapDAO} and {@code ClientSetDAO} for
 * client storage</li>
 * <li>Support for CRUD operations on Clients and Products</li>
 * <li>User interaction via graphical dialog prompts (JOptionPane)</li>
 * </ul>
 * 
 * <p>
 * All DAO interfaces and implementations follow the generic
 * {@code IGenericDAO<T>} contract. The business logic and interaction logic is
 * encapsulated within this class.
 * 
 * <p>
 * Known limitations:
 * <ul>
 * <li>Storage is in-memory (no persistence to file or database)</li>
 * <li>Simple input validation</li>
 * </ul>
 * 
 * @author Eduardo Augusto (https://github.com/AsrielDreemurrGM/)
 * @since May 21, 2025
 */
public class App {

	private static final String NOTINFORMEDERROR = "Não informado";
	private static final String ENTRYERROR = "Erro de Entrada";

	private static IClientDAO iClientDAO;
	private static IProductDAO iProductDAO;

	/**
	 * Entry point of the application.
	 * <p>
	 * Initializes the DAOs and launches the dashboard for Client or Product
	 * management. Presents a dialog to select whether to manage Clients or
	 * Products, and starts the interactive loop.
	 * 
	 * @param args command-line arguments (not used)
	 */
	public static void main(String[] args) {
		iClientDAO = new ClientMapDAO();
		iProductDAO = new ProductMapDAO();

		int entityChoice = MenuHelper.showEntitySelection();
		boolean isClient = MenuHelper.isClientSelected(entityChoice);

		runDashboardLoop(isClient);
	}

	/**
	 * Runs the main dashboard interaction loop.
	 * <p>
	 * Continues to show the dashboard until the user chooses to exit.
	 * 
	 * @param isClient {@code true} if managing Clients, {@code false} if managing
	 *                 Products
	 */
	private static void runDashboardLoop(boolean isClient) {
		String option = MenuHelper.showDashboardPrompt();

		while (!MenuHelper.isValidOption(option)) {
			if (option == null) {
				PromptHelper.exitPrompt();
			}
			option = MenuHelper.showInvalidOptionPrompt();
		}

		while (MenuHelper.isValidOption(option)) {
			if (MenuHelper.isExitOption(option)) {
				PromptHelper.exitPrompt();
			} else {
				handleOption(option, isClient);
			}
			option = MenuHelper.showDashboardPrompt();
		}
	}

	/**
	 * Handles a single user-selected option from the dashboard.
	 * <p>
	 * Depending on the selected option, invokes register, search, delete, or modify
	 * actions for either Clients or Products.
	 * 
	 * @param option   the selected option
	 * @param isClient {@code true} if managing Clients, {@code false} if managing
	 *                 Products
	 */
	private static void handleOption(String option, boolean isClient) {
		if (MenuHelper.isRegisterOption(option)) {
			String data = JOptionPane.showInputDialog(null, isClient
					? "Digite os dados do cliente separados por vírgula:\nNome, CPF, Telefone, Endereço, Número, Cidade, Estado"
					: "Digite os dados do produto separados por vírgula:\nNome, Código, Descrição, Valor, Marca",
					"Cadastrar", JOptionPane.INFORMATION_MESSAGE);

			if (isClient) {
				registerClient(data);
			} else {
				registerProduct(data);
			}

		} else if (MenuHelper.isSearchOption(option)) {
			String data = JOptionPane.showInputDialog(null, isClient ? "Digite o CPF:" : "Digite o código do produto:",
					"Pesquisar", JOptionPane.INFORMATION_MESSAGE);

			if (isClient) {
				searchClient(data);
			} else {
				searchProduct(data);
			}

		} else if (MenuHelper.isDeleteOption(option)) {
			String codeOrCpf = JOptionPane.showInputDialog(null,
					isClient ? "Digite o CPF do cliente a excluir:" : "Digite o código do produto a excluir:",
					"Excluir", JOptionPane.INFORMATION_MESSAGE);

			if (isClient) {
				deleteClient(codeOrCpf);
			} else {
				deleteProduct(codeOrCpf);
			}

		} else if (MenuHelper.isModifyOption(option)) {
			String codeOrCpf = JOptionPane.showInputDialog(null,
					isClient ? "Digite o CPF do cliente a alterar:" : "Digite o código do produto a alterar:",
					"Alterar", JOptionPane.INFORMATION_MESSAGE);

			if (isClient) {
				modifyClient(codeOrCpf);
			} else {
				modifyProduct(codeOrCpf);
			}
		}
	}

	/**
	 * Searches for a Client based on the provided CPF.
	 * <p>
	 * Displays client information if found, or an appropriate message if not found.
	 * 
	 * @param data the CPF to search for
	 */
	private static void searchClient(String data) {
		if (data == null || data.trim().isEmpty()) {
			PromptHelper.showMissingCpfPrompt();
			return;
		}

		Client client = iClientDAO.search(data);
		if (client != null) {
			JOptionPane.showMessageDialog(null, "Cliente encontrado. \n" + client.toString(), "Informações do Cliente",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			PromptHelper.showClientNotFoundPrompt();
		}
	}

	/**
	 * Registers a new Client based on the provided input string.
	 * <p>
	 * Parses the user input and fills missing values with "Não informado" (Not
	 * informed).
	 * 
	 * @param data input string containing client fields separated by commas
	 */
	private static void registerClient(String data) {
		if (data == null || data.trim().isEmpty()) {
			PromptHelper.showNoValueInsertedPrompt();
			return;
		}

		String[] splitData = data.split(",");

		for (int i = 0; i < splitData.length; i++) {
			String field = splitData[i].trim();
			if (field.isEmpty()) {
				splitData[i] = NOTINFORMEDERROR;
			} else {
				splitData[i] = field;
			}
		}

		while (splitData.length < 7) {
			int originalLength = splitData.length;
			splitData = java.util.Arrays.copyOf(splitData, 7);
			for (int i = originalLength; i < 7; i++) {
				splitData[i] = NOTINFORMEDERROR;
			}
		}

		Client client = new Client(splitData[0], splitData[1], splitData[2], splitData[3], splitData[4], splitData[5],
				splitData[6]);

		boolean isRegistered = iClientDAO.register(client);
		if (isRegistered) {
			JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "Sucesso no Cadastro",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Cliente já se encontra cadastrado", "Erro",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Deletes a Client based on the provided CPF.
	 * <p>
	 * If the Client exists, it is removed from the data store and the user is
	 * notified. If not found, a message is shown.
	 * 
	 * @param cpf the CPF of the Client to delete
	 */
	private static void deleteClient(String cpf) {
		if (cpf == null || cpf.trim().isEmpty()) {
			PromptHelper.showMissingCpfPrompt();
			return;
		}

		Client client = iClientDAO.search(cpf);
		if (client != null) {
			iClientDAO.delete(cpf);
			JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso.", "Sucesso na Exclusão",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			PromptHelper.showClientNotFoundPrompt();
		}
	}

	/**
	 * Modifies an existing Client based on the provided CPF.
	 * <p>
	 * Prompts the user for new values and updates the registered Client
	 * accordingly.
	 * 
	 * @param cpf the CPF of the Client to be modified
	 */
	private static void modifyClient(String cpf) {
		if (cpf == null || cpf.trim().isEmpty()) {
			PromptHelper.showMissingCpfPrompt();
			return;
		}

		Client registeredClient = iClientDAO.search(cpf);
		if (registeredClient == null) {
			PromptHelper.showClientNotFoundPrompt();
			return;
		}

		String newData = JOptionPane.showInputDialog(null,
				"Digite os novos dados separados por vírgula: \nNome, Telefone, Endereço, Número, Cidade, Estado",
				"Alterar Cliente", JOptionPane.INFORMATION_MESSAGE);
		if (newData == null || newData.trim().isEmpty()) {
			PromptHelper.showNoValueInsertedPrompt();
			return;
		}

		String[] splitData = newData.split(",");
		for (int i = 0; i < splitData.length; i++) {
			String field = splitData[i].trim();
			splitData[i] = field.isEmpty() ? NOTINFORMEDERROR : field;
		}

		while (splitData.length < 6) {
			int originalLength = splitData.length;
			splitData = java.util.Arrays.copyOf(splitData, 6);
			for (int i = originalLength; i < 6; i++) {
				splitData[i] = NOTINFORMEDERROR;
			}
		}

		Client updatedClient = new Client(splitData[0], cpf, splitData[1], splitData[2], splitData[3], splitData[4],
				splitData[5]);
		iClientDAO.updateEntity(updatedClient);
		JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso.", "Cliente Atualizado",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Searches for a Product based on the provided product code.
	 * <p>
	 * Displays product information if found, or an appropriate message if not
	 * found.
	 * 
	 * @param code the product code to search for
	 */
	private static void searchProduct(String code) {
		if (code == null || code.trim().isEmpty()) {
			PromptHelper.showMissingProductPrompt();
			return;
		}

		Product product = iProductDAO.search(code);
		if (product != null) {
			JOptionPane.showMessageDialog(null, "Produto encontrado:\n" + product.toString(), "Informações do Produto",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			PromptHelper.showProductNotFoundPrompt();
		}
	}

	/**
	 * Registers a new Product based on the provided input string.
	 * <p>
	 * Parses the user input and fills missing values with "Não informado". Attempts
	 * to parse the "Valor" field as a double.
	 * 
	 * @param data input string containing product fields separated by commas
	 */
	private static void registerProduct(String data) {
		if (data == null || data.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nenhum dado inserido.", ENTRYERROR, JOptionPane.WARNING_MESSAGE);
			return;
		}

		String[] splitData = data.split(",");

		for (int i = 0; i < splitData.length; i++) {
			String field = splitData[i].trim();
			if (field.isEmpty()) {
				splitData[i] = NOTINFORMEDERROR;
			} else {
				splitData[i] = field;
			}
		}

		while (splitData.length < 5) {
			int originalLength = splitData.length;
			splitData = java.util.Arrays.copyOf(splitData, 5);
			for (int i = originalLength; i < 5; i++) {
				splitData[i] = NOTINFORMEDERROR;
			}
		}

		try {
			double value = Double.parseDouble(splitData[3]);
			Product product = new Product(splitData[0], splitData[1], splitData[2], value, splitData[4]);
			boolean isRegistered = iProductDAO.register(product);
			if (isRegistered) {
				JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso.", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Produto já cadastrado.", "Erro", JOptionPane.WARNING_MESSAGE);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Valor inválido para o campo 'Valor'.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Deletes a Product based on the provided product code.
	 * <p>
	 * If the Product exists, it is removed from the data store and the user is
	 * notified. If not found, a message is shown.
	 * 
	 * @param code the product code of the Product to delete
	 */
	private static void deleteProduct(String code) {
		if (code == null || code.trim().isEmpty()) {
			PromptHelper.showMissingProductPrompt();
			return;
		}

		Product product = iProductDAO.search(code);
		if (product != null) {
			iProductDAO.delete(code);
			JOptionPane.showMessageDialog(null, "Produto excluído com sucesso.", "Sucesso na Exclusão",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			PromptHelper.showProductNotFoundPrompt();
		}
	}

	/**
	 * Modifies an existing Product based on the provided product code.
	 * <p>
	 * Prompts the user for new values and updates the registered Product
	 * accordingly. Attempts to parse the "Valor" field as a double.
	 * 
	 * @param code the code of the Product to be modified
	 */
	private static void modifyProduct(String code) {
		if (code == null || code.trim().isEmpty()) {
			PromptHelper.showMissingProductPrompt();
			return;
		}

		Product registeredProduct = iProductDAO.search(code);
		if (registeredProduct == null) {
			PromptHelper.showProductNotFoundPrompt();
			return;
		}

		String newData = JOptionPane.showInputDialog(null,
				"Digite os novos dados separados por vírgula:\nNome, Descrição, Valor, Marca", "Modificar Produto",
				JOptionPane.INFORMATION_MESSAGE);

		if (newData == null || newData.trim().isEmpty()) {
			PromptHelper.showNoValueInsertedPrompt();
			return;
		}

		String[] splitData = newData.split(",");
		for (int i = 0; i < splitData.length; i++) {
			String field = splitData[i].trim();
			splitData[i] = field.isEmpty() ? NOTINFORMEDERROR : field;
		}

		while (splitData.length < 4) {
			int originalLength = splitData.length;
			splitData = java.util.Arrays.copyOf(splitData, 4);
			for (int i = originalLength; i < 4; i++) {
				splitData[i] = NOTINFORMEDERROR;
			}
		}

		try {
			double value = Double.parseDouble(splitData[2]);
			Product updatedProduct = new Product(splitData[0], code, splitData[1], value, splitData[3]);

			iProductDAO.updateEntity(updatedProduct);
			JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso.", "Produto Atualizado",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Valor inválido para o campo 'Valor'.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
