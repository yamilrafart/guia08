package frsf.isi.died.guia08.problema01.modelo;

import java.time.LocalDateTime;

public class Tarea {

	private Integer id;
	private String descripcion;
	private Integer duracionEstimada;
	private Empleado empleadoAsignado;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private Boolean facturada;
	
	
	
	/**
	 * CONSTRUCTOR BASE
	 */
	public Tarea() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * CONTRUCTOR CAMPOS
	 * @param id
	 * @param descripcion
	 * @param duracionEstimada
	 */
	public Tarea(Integer id, String descripcion, Integer duracionEstimada) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.duracionEstimada = duracionEstimada;
		this.facturada = false;
	}

	public void asignarEmpleado(Empleado e) throws Exception{
		// si la tarea ya tiene un empleado asignado
		// y tiene fecha de finalizado debe lanzar una excepcion
		if (this.empleadoAsignado != null || this.fechaFin != null) {
			throw new AsignacionTareaException("La tarea ya tiene un empleado asignado y/o ya fue finalizada.");
		}
	}

	
	
	//GETTERS - SETTERS
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the duracionEstimada
	 */
	public Integer getDuracionEstimada() {
		return duracionEstimada;
	}

	/**
	 * @param duracionEstimada the duracionEstimada to set
	 */
	public void setDuracionEstimada(Integer duracionEstimada) {
		this.duracionEstimada = duracionEstimada;
	}

	/**
	 * @return the empleadoAsignado
	 */
	public Empleado getEmpleadoAsignado() {
		return empleadoAsignado;
	}

	/**
	 * @param empleadoAsignado the empleadoAsignado to set
	 */
	public void setEmpleadoAsignado(Empleado empleadoAsignado) {
		this.empleadoAsignado = empleadoAsignado;
	}

	/**
	 * @return the fechaInicio
	 */
	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the facturada
	 */
	public Boolean getFacturada() {
		return facturada;
	}

	/**
	 * @param facturada the facturada to set
	 */
	public void setFacturada(Boolean facturada) {
		this.facturada = facturada;
	}
	
}
