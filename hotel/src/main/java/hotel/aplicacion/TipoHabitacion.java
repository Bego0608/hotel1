/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.aplicacion;

/**
 * Esta clase es un enum que representa los tipos de habitaciones disponibles, que en este caso son Doble o Suite.
 * Este enum es utilizado en la clase Reserva para definir el tipo de habitación que se reserva.
 * @author Maria Begoña Madrid
 */

public enum TipoHabitacion {
	/**
	 * Representa una habitación doble, con dos camas individuales o una de matrimonio.
	 */
    DOBLE, 
    
    /**
     * Representa una habitación suite, de mayor categoría que la anterior.
     */
    SUITE;
}