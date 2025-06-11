package br.com.eaugusto.reflections.annotations.ui;

import javax.swing.JOptionPane;

/**
 * Utility class for handling general prompts and error dialogs in the CRUD
 * application using JOptionPane.
 * 
 * <p>
 * This class is not meant to be instantiated.
 * </p>
 * 
 * @author Eduardo Augusto (https://github.com/AsrielDreemurrGM/)
 * @since June 04, 2025
 */
public class PromptHelper {

	private PromptHelper() {
		// Utility class, must not be instantiated.
	}

	/**
	 * Displays an exit message and terminates the application.
	 */
	public static void exitPrompt() {
		JOptionPane.showMessageDialog(null, "Até logo :) ", "Saindo", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	/**
	 * Shows the dashboard input prompt for user interaction.
	 * 
	 * @return The option entered by the user.
	 */
	public static String showDashboardPrompt() {
		return JOptionPane.showInputDialog(null,
				"Digite 1 para cadastro, 2 para consultar, 3 para exclusão, " + "4 para alteração ou 5 para sair.",
				"Dashboard", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Shows a warning when the user enters an invalid option.
	 * 
	 * @return The new option entered by the user.
	 */
	public static String showInvalidOptionPrompt() {
		return JOptionPane.showInputDialog(null, """
				Opção inválida. Por favor, escolha entre:
				1 - Cadastrar 2 - Consultar 3 - Excluir 4 - Alterar 5 - Sair
				""", "Opção Inválida", JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Shows a warning when the CPF field is left empty.
	 */
	public static void showMissingCpfPrompt() {
		JOptionPane.showMessageDialog(null, "CPF não pode estar vazio.", "CPF - Erro de Entrada",
				JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Shows a warning when the product code field is left empty.
	 */
	public static void showMissingProductPrompt() {
		JOptionPane.showMessageDialog(null, "Código do produto não pode estar vazio.", "Erro de Entrada",
				JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Informs the user that the requested client was not found.
	 */
	public static void showClientNotFoundPrompt() {
		JOptionPane.showMessageDialog(null, "Cliente não encontrado.", "Erro - Cliente Não Encontrado",
				JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Informs the user that the requested product was not found.
	 */
	public static void showProductNotFoundPrompt() {
		JOptionPane.showMessageDialog(null, "Produto não encontrado.", "Erro - Produto Não Encontrado",
				JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Shows a warning when no input value was provided.
	 */
	public static void showNoValueInsertedPrompt() {
		JOptionPane.showMessageDialog(null, "Nenhum valor foi inserido.", "Erro de Entrada",
				JOptionPane.WARNING_MESSAGE);
	}
}
