package frsf.isi.died.guia08.problema01.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Empleado {

	public enum Tipo {CONTRATADO,EFECTIVO}; 
	
	private Integer cuil;
	private String nombre;
	private Tipo tipo;
	private Double costoHora;
	private List<Tarea> tareasAsignadas;
	
	private Function<Tarea, Double> calculoPagoPorTarea;		
	private Predicate<Tarea> puedeAsignarTarea;

	
	
	/**
	 * CONSTRUCTOR BASE
	 */
	public Empleado() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * CONSTRUCTOR CAMPOS
	 * @param cuil
	 * @param nombre
	 * @param tipo
	 * @param costoHora
	 */
	public Empleado(Integer cuil, String nombre, Tipo tipo, Double costoHora) {
		super();
		this.cuil = cuil;
		this.nombre = nombre;
		this.tipo = tipo;
		this.costoHora = costoHora;
		this.tareasAsignadas = new ArrayList<Tarea>();
		switch (this.tipo) {
		case CONTRATADO:
			this.puedeAsignarTarea = (Tarea t1)->this.tareasAsignadas.stream().filter((Tarea t2)->t2.getFechaFin()==null).count()<=5;
			break;
		case EFECTIVO:
			this.puedeAsignarTarea = (Tarea t1)->this.tareasAsignadas.stream().filter((Tarea t2)->t2.getFechaFin()==null).mapToInt((Tarea t4)->t4.getDuracionEstimada()).sum()<=15;
			break;
		}
	}

	public Double salario() {
		// cargar todas las tareas no facturadas
		// calcular el costo
		// marcarlas como facturadas.
		List<Tarea>tareasNoFacturadas = this.tareasAsignadas.stream().filter((Tarea t)->!t.getFacturada()).collect(Collectors.toList());
		tareasNoFacturadas.stream().forEach((Tarea t)->t.setFacturada(true));
		return tareasNoFacturadas.stream().mapToDouble((Tarea t)->this.costoTarea(t)).sum();
	}
	
	/**
	 * Si la tarea ya fue terminada nos indica cuaal es el monto según el algoritmo de calculoPagoPorTarea
	 * Si la tarea no fue terminada simplemente calcula el costo en base a lo estimado.
	 * @param t
	 * @return
	 */
	public Double costoTarea(Tarea t) {
		
		return 0.0;
	}
		
	public Boolean asignarTarea(Tarea t) {
		try {
			t.asignarEmpleado(this);
		} catch (Exception e) {
			System.out.println("Error al asignar tarea: " + e.getMessage());
//			e.printStackTrace();
			return false;
		}
		if(this.puedeAsignarTarea.test(t)) {
			this.tareasAsignadas.add(t);
			return true;
		} else {
			return false;
		}
	}
	
	public void comenzar(Integer idTarea) {
		// busca la tarea en la lista de tareas asignadas 
		// si la tarea no existe lanza una excepción
		// si la tarea existe indica como fecha de inicio la fecha y hora actual
	}
	
	public void finalizar(Integer idTarea) {
		// busca la tarea en la lista de tareas asignadas 
		// si la tarea no existe lanza una excepción
		// si la tarea existe indica como fecha de finalizacion la fecha y hora actual
	}

	public void comenzar(Integer idTarea,String fecha) {
		// busca la tarea en la lista de tareas asignadas 
		// si la tarea no existe lanza una excepción
		// si la tarea existe indica como fecha de finalizacion la fecha y hora actual
	}
	
	public void finalizar(Integer idTarea,String fecha) {
		// busca la tarea en la lista de tareas asignadas 
		// si la tarea no existe lanza una excepción
		// si la tarea existe indica como fecha de finalizacion la fecha y hora actual
	}

	
	
	//GETTERS - SETTERS
	/**
	 * @return the cuil
	 */
	public Integer getCuil() {
		return cuil;
	}

	/**
	 * @param cuil the cuil to set
	 */
	public void setCuil(Integer cuil) {
		this.cuil = cuil;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the tipo
	 */
	public Tipo getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the costoHora
	 */
	public Double getCostoHora() {
		return costoHora;
	}

	/**
	 * @param costoHora the costoHora to set
	 */
	public void setCostoHora(Double costoHora) {
		this.costoHora = costoHora;
	}

	/**
	 * @return the tareasAsignadas
	 */
	public List<Tarea> getTareasAsignadas() {
		return tareasAsignadas;
	}

	/**
	 * @param tareasAsignadas the tareasAsignadas to set
	 */
	public void setTareasAsignadas(List<Tarea> tareasAsignadas) {
		this.tareasAsignadas = tareasAsignadas;
	}

	/**
	 * @return the calculoPagoPorTarea
	 */
	public Function<Tarea, Double> getCalculoPagoPorTarea() {
		return calculoPagoPorTarea;
	}

	/**
	 * @param calculoPagoPorTarea the calculoPagoPorTarea to set
	 */
	public void setCalculoPagoPorTarea(Function<Tarea, Double> calculoPagoPorTarea) {
		this.calculoPagoPorTarea = calculoPagoPorTarea;
	}

	/**
	 * @return the puedeAsignarTarea
	 */
	public Predicate<Tarea> getPuedeAsignarTarea() {
		return puedeAsignarTarea;
	}

	/**
	 * @param puedeAsignarTarea the puedeAsignarTarea to set
	 */
	public void setPuedeAsignarTarea(Predicate<Tarea> puedeAsignarTarea) {
		this.puedeAsignarTarea = puedeAsignarTarea;
	}
	
}
