package frsf.isi.died.guia08.problema01;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import frsf.isi.died.guia08.problema01.modelo.AsignacionTareaException;
import frsf.isi.died.guia08.problema01.modelo.Empleado;
import frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo;
import frsf.isi.died.guia08.problema01.modelo.Tarea;

public class AppRRHH {

	private List<Empleado> empleados;
	
	/**
	 * crear un empleado
	 * agregarlo a la lista
	 * @param cuil
	 * @param nombre
	 * @param costoHora
	 */
	public void agregarEmpleadoContratado(Integer cuil,String nombre,Double costoHora) {
		this.empleados.add(new Empleado(cuil, nombre, Tipo.CONTRATADO, costoHora));
	}
	
	/**
	 * crear un empleado
	 * agregarlo a la lista 
	 * @param cuil
	 * @param nombre
	 * @param costoHora
	 */
	public void agregarEmpleadoEfectivo(Integer cuil,String nombre,Double costoHora) {
		this.empleados.add(new Empleado(cuil, nombre, Tipo.EFECTIVO, costoHora));
	}
	
	/**
	 * crear un empleado
	 * con el método buscarEmpleado() de esta clase
	 * agregarlo a la lista	
	 * @param cuil
	 * @param idTarea
	 * @param descripcion
	 * @param duracionEstimada
	 */
	public void asignarTarea(Integer cuil,Integer idTarea,String descripcion,Integer duracionEstimada) {
		Empleado empleado = this.buscarEmpleado((Empleado e1)->e1.getCuil().equals(cuil)).orElse(null);
		if (empleado != null) {
			Tarea t = new Tarea(idTarea, descripcion, duracionEstimada);
			try {
				empleado.asignarTarea(t);
			} catch (AsignacionTareaException e) {
				System.out.println("Error al asignar tarea: " +e.getMessage());
//				e.printStackTrace();
			}
			
		}
	}
	
	public void empezarTarea(Integer cuil,Integer idTarea) {
		// busca el empleado por cuil en la lista de empleados
		// con el método buscarEmpleado() actual de esta clase
		// e invoca al método comenzar tarea
	}
	
	public void terminarTarea(Integer cuil,Integer idTarea) {
		// crear un empleado
		// agregarlo a la lista		
	}

	public void cargarEmpleadosContratadosCSV(String nombreArchivo) {
		// leer datos del archivo
		// por cada fila invocar a agregarEmpleadoContratado
	}

	public void cargarEmpleadosEfectivosCSV(String nombreArchivo) {
		// leer datos del archivo
		// por cada fila invocar a agregarEmpleadoContratado		
	}

	public void cargarTareasCSV(String nombreArchivo) {
		// leer datos del archivo
		// cada fila del archivo tendrá:
		// cuil del empleado asignado, numero de la taera, descripcion y duración estimada en horas.
	}
	
	private void guardarTareasTerminadasCSV() {
		// guarda una lista con los datos de la tarea que fueron terminadas
		// y todavía no fueron facturadas
		// y el nombre y cuil del empleado que la finalizó en formato CSV 
	}
	
	private Optional<Empleado> buscarEmpleado(Predicate<Empleado> p){
		return this.empleados.stream().filter(p).findFirst();
	}

	public Double facturar() {
		this.guardarTareasTerminadasCSV();
		return this.empleados.stream()				
				.mapToDouble(e -> e.salario())
				.sum();
	}
}
