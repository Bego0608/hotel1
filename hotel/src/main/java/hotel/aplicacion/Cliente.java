/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.aplicacion;

/**
 * Clase que sirve de representación de un cliente para el sistema de gestión de reservas.
 * Cada cliente tiene su propio código único, su nombre, DNI y teléfono.
 * Los métodos de esta clase sirven para mostrar la información del cliente, accediendo a sus atributos y validando su DNI.
 * @author María Begoña Madrid
 */
public class Cliente {
    private static int contadorClientes = 0; // Contador para asignar un código único a cada cliente.
	private int codigo; // Código único del cliente
	private String nombre; // Nombre del cliente
    private String dni; // DNI del cliente 
    private String telefono; // Teléfono del cliente
    
    
    /**
     * Constructor de la clase cliente para inicializar los atributos cn los valores proporcionados.
     * Realiza una validación del DNI a través de la clase Utilidades, si el DNI no es válido, lanza una excepción.
     * 
     * @param nombre nombre del cliente
     * @param dni DNI del cliente
     * @param telefono Teléfono del cliente
     * @throws Exception Si el DNI no es válido según la validación de la clase Utilidades
     */
    public Cliente(String nombre, String dni, String telefono) throws Exception {
        // Validación del DNI, si no es correcto no creará el objeto
        Utilidades.validarDNI(dni); 
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.codigo = obtenerNumeroCliente();
    }
    	
    /**
     * Obtiene el siguiente número de cliente incrementado el contador.
     * Este método es privado ya que solo se usa para asignarle un código único a cada cliente.
     * 
     * @return el siguiente número de cliente
     */
        private static int obtenerNumeroCliente() {
        contadorClientes++;
        return contadorClientes;
    }

    /**
     * Este método devuelve una cadena con la información básica del cliente.
     * Incluye el código, nombre, DNI y el teléfono.
     * 
     * @return Una cadena con la información del cliente.
     */
    public String mostrarInformacion() {
        return "Código: " + codigo + ", Nombre: " + nombre + ", DNI: " + dni + ", Teléfono: " + telefono;
    }
    
    public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * Obtiene el contador de clientes, que lleva un  seguimiento del número total de clientes.
	 * 
	 * @return El número total de clientes creados hasta el momento.
	 */
	public static int getContadorClientes() {
		return contadorClientes;
	}

	
}