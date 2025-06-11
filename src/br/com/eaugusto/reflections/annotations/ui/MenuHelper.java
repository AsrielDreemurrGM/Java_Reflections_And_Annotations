package br.com.eaugusto.reflections.annotations.ui;

import javax.swing.JOptionPane;

/**
 * Utility class for handling UI interactions related to the main dashboard menu
 * and entity selection in the CRUD application. Uses JOptionPane dialogs for
 * user input and interaction.
 * 
 * <p>
 * This class is not meant to be instantiated.
 * </p>
 * 
 * @author Eduardo Augusto (https://github.com/AsrielDreemurrGM/)
 * @since June 04, 2025
 */
public class MenuHelper {

	private MenuHelper() {
		// Utility class, must not be instantiated.
	}

	/** Options to choose between client or product entity. */
	private static final Object[] ENTITY_OPTIONS = { "Cliente", "Produto" };

	/** List of valid dashboard option values. */
	private static final String[] DASHBOARD_OPTIONS = { "1", "2", "3", "4", "5" };

	/**
	 * Checks whether the given input is a valid dashboard option.
	 * 
	 * @param option The user input option.
	 * @return true if it matches one of the valid options; false otherwise.
	 */
	public static boolean isValidOption(String option) {
		if (option == null || option.trim().isEmpty()) {
			return false;
		}
		for (String validOption : DASHBOARD_OPTIONS) {
			if (validOption.equals(option.trim())) {
				return true;
			}
		}
		return false;
	}

	/** @return true if the option is for registering a new entity. */
	public static boolean isRegisterOption(String option) {
		return "1".equals(option);
	}

	/** @return true if the option is for searching an entity. */
	public static boolean isSearchOption(String option) {
		return "2".equals(option);
	}

	/** @return true if the option is for deleting an entity. */
	public static boolean isDeleteOption(String option) {
		return "3".equals(option);
	}

	/** @return true if the option is for modifying an entity. */
	public static boolean isModifyOption(String option) {
		return "4".equals(option);
	}

	/** @return true if the option is to exit the application. */
	public static boolean isExitOption(String option) {
		return "5".equals(option);
	}

	/**
	 * Checks whether the user selected "Cliente" as the entity.
	 * 
	 * @param entityChoice The integer value returned from JOptionPane.
	 * @return true if "Cliente" (Client) is selected; false if "Produto" (Product).
	 */
	public static boolean isClientSelected(int entityChoice) {
		return entityChoice == 0;
	}

	/**
	 * Shows a dialog for selecting the entity type.
	 * 
	 * @return 0 if "Cliente" is selected; 1 if "Produto"; -1 if cancelled.
	 */
	public static int showEntitySelection() {
		return JOptionPane.showOptionDialog(null, "Você quer trabalhar com Cliente ou Produto?", "Escolha de Entidade",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, ENTITY_OPTIONS, ENTITY_OPTIONS[0]);
	}

	/**
	 * Shows the main dashboard menu prompt.
	 * 
	 * @return The option selected by the user.
	 */
	public static String showDashboardPrompt() {
		return JOptionPane.showInputDialog(null, "Escolha uma opção:\n" + "1 - Cadastrar " + "2 - Buscar "
				+ "3 - Excluir " + "4 - Alterar " + "5 - Sair", "Menu Principal", JOptionPane.QUESTION_MESSAGE);
	}

	/**
	 * Shows an error prompt when the user inputs an invalid option.
	 * 
	 * @return The new option input by the user.
	 */
	public static String showInvalidOptionPrompt() {
		return JOptionPane
				.showInputDialog(
						null, "Opção inválida. Digite uma opção válida:\n" + "1 - Cadastrar " + "2 - Buscar "
								+ "3 - Excluir " + "4 - Alterar " + "5 - Sair",
						"Opção Inválida", JOptionPane.WARNING_MESSAGE);
	}
}
