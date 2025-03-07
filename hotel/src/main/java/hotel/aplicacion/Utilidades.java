/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.aplicacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Esta clase contiene métodos estáticos de utilidad utilizados en el programa, como la validación de un dni, 
 * la lectura y validación de las fechas de entrada y salida para las reservas.
 * Estos métodos son usados principalmente en la creación de clientes y reservas.
 *
 * @author Maria Begoña Madrid
 */
public class Utilidades {
  
	/**
	 * Valida un DNI, verificando que tenga bien su formato y que la letra corresponda con el número.
	 * 
	 * @param dni El DNI a validar.
	 * @throws Exception Si el dni no tiene el formato correcto o la letra no corresponde con el número.
	 */
    public static void validarDNI(String dni) throws Exception {
        if (dni == null || dni.length() != 9) {
            throw new Exception("El DNI debe tener 9 caracteres (8 números y 1 letra)");
        }
        String numeros = dni.substring(0, 8);
        char letra = dni.charAt(8);
        if (!numeros.matches("\\d{8}") || !Character.isUpperCase(letra)) {
            throw new Exception("El formato del DNI es incorrecto. Debe contener 8 números seguidos de una letra.");
        }
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = Integer.parseInt(numeros);
        int resto = numero % 23;
        char letraCorrecta = letras.charAt(resto);
        if (letra != letraCorrecta) {
            throw new Exception("La letra del DNI no es válida para el número proporcionado");
        }
    }

    /**
     * Lee la fecha que introduce el usuario por el teclado, si no tiene el formato correcto, envía un mensaje al usuario 
     * para que vuelva a introducir una fecha correcta. 
     * 
     * @param mensaje El mensaje que se muestra al usuario para pedirle la fecha.
     * @return La fecha introducida por el usuario, es un objeto LocalDate.
     */
    public static LocalDate leerFecha(String mensaje) {
        Scanner sc = new Scanner(System.in);
        LocalDate fecha = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            try {
                System.out.print(mensaje + " (formato yyyy-MM-dd): ");
                String input = sc.nextLine();
                fecha = LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Fecha inválida. Por favor, introduzca la fecha en formato yyyy-MM-dd.");
            }
        }
        return fecha;
    }
    
    /**
     * Valida que la fecha de entrada no sea anterior a la fecha actual y que la fecha de salida no sea anterior a
     * la fecha de entrada.
     * 
     * @param fechaInicio La fecha de entrada
     * @param fechaFin La fecha de salida
     * @throws Exception Di la fecha de entrada es anterior a la fecha actual o si la fecha de salida es anterior 
     * 			a la fecha de entrada
     */
    public static void validarFechas(LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
        LocalDate hoy = LocalDate.now();
        if (fechaInicio.isBefore(hoy)) {
            throw new Exception("La fecha de entrada no puede ser anterior a la fecha actual.");
        }
        if (!fechaFin.isAfter(fechaInicio)) {
            throw new Exception("La fecha de salida debe ser posterior a la fecha de entrada.");
        }
    }
}
