/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.aplicacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Esta clase representa una reserva del hotel. Incluye toda la información sobre esta reserva, como los datos del cliente, las fechas
 * de entrada y de salida, el tipo de habitación, si se solicita una cama supletoria y el coste de la reserva, aplicando, en su caso, un 
 * posible descuento si la estanca es de más de 7 días.
 * 
 * @author Maria Begoña Madrid
 */
public class Reserva {
    private static int contadorReservas = 0; // Contador de reservas
    private int codigoReserva; // Código único de la reserva
    private Cliente cliente; // Cliente que realiza la reserva 
    private LocalDate fechaEntrada; // Fecha de entrada de la reserva
    private LocalDate fechaSalida; // Fecha de salida de la reserva
    private TipoHabitacion tipoHabitacion; // Tipo de habitación de la reserva
    private boolean camaSupletoria; // Indica si se solicita una cama supletoria en la habitación de la reserva
    private double costeTotal; // Coste total de la reserva
    
    // Constantes fijas para el precio
    private static final double PRECIO_DOBLE = 50.0; // Precio de la habitación doble
    private static final double PRECIO_SUITE = 100.0; //Precio de la suite
    private static final double RECARGO_CAMA_SUPLETORIA = 20.0; // Precio de la cama supletoria

    	/**
    	 * Constructor de la clase Reserva.
    	 * 
    	 * @param cliente cliente que hace la reserva.
    	 * @param fechaEntrada fecha de entrada a la habitación.
    	 * @param fechaSalida fecha de salida de la habitación.
    	 * @param tipoHabitacion tipo de habitación reservada (doble o suite).
    	 * @param camaSupletoria indica si se solicita una cama supletoria.
    	 * @throws Exception Si la fecha de entrada es posterior a la de salida, lanza una excepción.
    	 */
      	public Reserva(Cliente cliente, LocalDate fechaEntrada, LocalDate fechaSalida,
                   TipoHabitacion tipoHabitacion, boolean camaSupletoria) throws Exception {
        if (!fechaSalida.isAfter(fechaEntrada)) {
            throw new Exception("La fecha de salida debe ser posterior a la de entrada.");
        }
        this.codigoReserva = obtenerCodigoReserva();
        this.cliente = cliente;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.tipoHabitacion = tipoHabitacion;
        this.camaSupletoria = camaSupletoria;
        this.costeTotal = calcularCosteTotal();
    }

    /**
     * Obtiene el código único de reserva, mediante un contador.
     * 
     * @return Código único de la reserva.
     */
    private static int obtenerCodigoReserva() {
        contadorReservas++;
        return contadorReservas;
    }

    /**
     * Calcula el coste total de la reserva, que depende del tipo de habitación, el número de días de la reserva,
     * o si se ha solicitado una cama supletoria.
     * Si la reserva es de más de 7 días se le aplica un descuento del 10%.
     * 
     * @return El coste total de la reserva.
     */
    public double calcularCosteTotal() {
        long noches = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
        double precioNoche = (tipoHabitacion == TipoHabitacion.DOBLE) ? PRECIO_DOBLE : PRECIO_SUITE;
        
        if (camaSupletoria) {
            precioNoche += RECARGO_CAMA_SUPLETORIA;
        }
        double total = noches * precioNoche;
        if (noches > 7) {
            total *= 0.9; // Aplica un descuento del 10%
        }
        return total;
    }

    /**
     * Muestra los detalles de la reserva con la información del cliente, las fechas de entrada y salida, el tipo de habitación,
     * si se ha solicitado una cama supletoria y el coste total de la reserva.
     * 
     * @return Una cadena con todos los detalles de la reserva.
     */
    public String mostrarDetalles() {
        String detalles = "Código Reserva: " + codigoReserva + "\n" +
                          "Cliente: " + cliente.mostrarInformacion() + "\n" +
                          "Fecha de Entrada: " + fechaEntrada + "\n" +
                          "Fecha de Salida: " + fechaSalida + "\n" +
                          "Tipo de Habitación: " + tipoHabitacion + "\n" +
                          "Cama Supletoria: " + (camaSupletoria ? "Sí" : "No") + "\n" +
                          "Coste Total: " + costeTotal + "Euros";
        return detalles;
    }

    // Getters y setters
    
    public int getCodigoReserva() {
        return codigoReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public boolean isCamaSupletoria() {
        return camaSupletoria;
    }

    public double getCosteTotal() {
        return costeTotal;
    }
    
    /**
     * Establece la fecha de entrada de la reserva.
     * La fecha de salida debe ser posterior a esta.
     * 
     * @param fechaEntrada Fecha de entrada a la habitación.
     * @throws Exception Si la fecha de salida es anterior a la fecha de entrada, lanza una excepción.
     */
    public void setFechaEntrada(LocalDate fechaEntrada) throws Exception {
        if (fechaSalida != null && !fechaSalida.isAfter(fechaEntrada)) {
            throw new Exception("La fecha de salida debe ser posterior a la de entrada.");
        }
        this.fechaEntrada = fechaEntrada;
        this.costeTotal = calcularCosteTotal();
    }

    /**
     * Establece la fecha de salida de la reserva.
     * Esta fecha debe ser posterior a la fecha de entrada de la reserva.
     * 
     * @param fechaSalida Fecha de salida de la reserva.
     * @throws Exception Si la fecha de salida es anterior a la fecha de entrada, lanza una expción.
     */
    public void setFechaSalida(LocalDate fechaSalida) throws Exception {
        if (fechaEntrada != null && !fechaSalida.isAfter(fechaEntrada)) {
            throw new Exception("La fecha de salida debe ser posterior a la de entrada.");
        }
        this.fechaSalida = fechaSalida;
        this.costeTotal = calcularCosteTotal();
    }
}}