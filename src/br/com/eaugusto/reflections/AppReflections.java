/**
 * @author Eduardo Augusto (https://github.com/AsrielDreemurrGM/)
 * @since Jun 10, 2025
 */

package br.com.eaugusto.reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AppReflections {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {

		System.out.println("Getting The Product Class Directly");
		Class productClass = Product.class;

		System.out.println(productClass);

		System.out.println("\nGetting The Product Class By Instantiating It First");
		Product product = new Product();
		Class productClass2 = product.getClass();
		System.out.println(productClass2);

		try {
			System.out.println("\nGetting The Product Class Constructor");
			Constructor cons = productClass.getConstructor();
			System.out.println("Constructor: " + cons);

			System.out.println("\nGetting The Product Created Through The Constructor");
			Product prod1 = (Product) cons.newInstance();
			System.out.println("Product: " + prod1);

			System.out.println("\nGetting All The Product's Properties And Their Respective Types");
			Field[] fields = prod1.getClass().getDeclaredFields();
			for (Field eachField : fields) {
				String name = eachField.getName();
				Class<?> type = eachField.getType();
				System.out.println("Field Name: " + name + " | Type: " + type);
			}

			System.out.println("\nGetting All The Product's Methods And Their Respective Return Types");
			Method[] methods = prod1.getClass().getDeclaredMethods();
			for (Method eachMethod : methods) {
				String name = eachMethod.getName();
				Class<?> type = eachMethod.getReturnType();
				System.out.println("Method Name: " + name + " | Return Type: " + type);
			}

		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
