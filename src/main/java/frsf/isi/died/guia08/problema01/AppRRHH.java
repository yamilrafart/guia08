package frsf.isi.died.guia08.problema01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import frsf.isi.died.guia08.problema01.modelo.AsignacionTareaException;
import frsf.isi.died.guia08.problema01.modelo.Empleado;
import frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo;
import frsf.isi.died.guia08.problema01.modelo.Tarea;
import frsf.isi.died.guia08.problema01.modelo.TareaInexistenteException;

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
	
	/**
	 * busca el empleado por cuil en la lista de empleados
	 * con el método buscarEmpleado() actual de esta clase
	 * e invoca al método comenzar tarea
	 * @param cuil
	 * @param idTarea
	 */
	public void empezarTarea(Integer cuil,Integer idTarea) {
		Empleado empleado = this.buscarEmpleado((Empleado e1)->e1.getCuil().equals(cuil)).orElse(null);
		try {
			empleado.comenzar(idTarea);
		} catch (TareaInexistenteException e) {
			System.out.println("Error al empezar tarea: " +e.getMessage());
//			e.printStackTrace();
		}
	}
	
	/**
	 * busca el empleado por cuil en la lista de empleados
	 * con el método buscarEmpleado() actual de esta clase
	 * e invoca al método finalizar tarea
	 * @param cuil
	 * @param idTarea
	 */
	public void terminarTarea(Integer cuil,Integer idTarea) {
		Empleado empleado = this.buscarEmpleado((Empleado e1)->e1.getCuil().equals(cuil)).orElse(null);
		try {
			empleado.finalizar(idTarea);
		} catch (TareaInexistenteException e) {
			System.out.println("Error al empezar tarea: " +e.getMessage());
//			e.printStackTrace();
		}
	}

	/**
	 * leer datos del archivo
	 * por cada fila invocar a agregarEmpleadoContratado
	 * @param nombreArchivo
	 */
	public void cargarEmpleadosContratadosCSV(String nombreArchivo) {
		try (BufferedReader rd = new BufferedReader(new FileReader(nombreArchivo))) {
				String inputLine = null;
				while((inputLine = rd.readLine()) != null) {
					String[] s = inputLine.split(";");
					this.agregarEmpleadoContratado(Integer.valueOf(s[0]),s[1],Double.valueOf(s[2]));
				}
		} catch (IOException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}

	/**
	 * leer datos del archivo
	 * por cada fila invocar a agregarEmpleadoContratado 
	 * @param nombreArchivo
	 */
	public void cargarEmpleadosEfectivosCSV(String nombreArchivo) {
		try (BufferedReader rd = new BufferedReader(new FileReader(nombreArchivo))) {
			String inputLine = null;
			while((inputLine = rd.readLine()) != null) {
				String[] s = inputLine.split(";");
				this.agregarEmpleadoEfectivo(Integer.valueOf(s[0]),s[1],Double.valueOf(s[2]));
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}		
	}

	/**
	 * leer datos del archivo
	 * cada fila del archivo tendrá:
	 * cuil del empleado asignado, numero de la taera, descripcion y duración estimada en horas.
	 * @param nombreArchivo
	 */
	public void cargarTareasCSV(String nombreArchivo) {
		try (BufferedReader rd = new BufferedReader(new FileReader(nombreArchivo))) {
			String inputLine = null;
			while((inputLine = rd.readLine()) != null) {
				String[] s = inputLine.split(";");
				this.asignarTarea(Integer.valueOf(s[3]), Integer.valueOf(s[0]),s[1],Integer.valueOf(s[2]));
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
	
	/**
	 * guarda una lista con los datos de la tarea que fueron terminadas
	 * y todavía no fueron facturadas
	 * y el nombre y cuil del empleado que la finalizó en formato CSV 
	 */
	private void guardarTareasTerminadasCSV() {
		try(BufferedWriter bf=new BufferedWriter(new FileWriter("tareasTerminadasNoFacturar.csv"))) {
			for (Empleado empleado : this.empleados) {
				for (Tarea tarea : empleado.getTareasAsignadas()) {
					if (!tarea.getFacturada() && tarea.getFechaFin()!=null) {
						bf.write(tarea.asCsv()+System.getProperty("line.separator"));
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	private Optional<Empleado> buscarEmpleado(Predicate<Empleado> p){
		return this.empleados.stream().filter(p).findFirst();
	}

	/**
	 * 
	 * @return
	 */
	public Double facturar() {
		this.guardarTareasTerminadasCSV();
		return this.empleados.stream()				
				.mapToDouble(e -> e.salario())
				.sum();
	}
}
