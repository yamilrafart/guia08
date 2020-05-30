package frsf.isi.died.guia08.problema01;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import frsf.isi.died.guia08.problema01.modelo.Empleado;
import frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo;

public class AppRRHHTest {

	@Test
	public void testAgregarEmpleadoContratado() {
		AppRRHH app = new AppRRHH();
		app.agregarEmpleadoContratado(1, "Yamil", 1000D);
		assertEquals(app.getEmpleados().size(), 1);
	}
	
	@Test
	public void testAgregarEmpleadoEfectivo() {
		AppRRHH app = new AppRRHH();
		app.agregarEmpleadoEfectivo(1, "Yamil", 1000D);
		assertEquals(app.getEmpleados().size(), 1);
	}
	
	@Test
	public void testAsignarTarea() {
		AppRRHH app = new AppRRHH();
		app.agregarEmpleadoContratado(1, "Yamil", 1000D);
		try {
			app.asignarTarea(1, 1, "t1", 20);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		assertEquals(app.getEmpleados().get(0).getTareasAsignadas().size(),1);
	}
	
	@Test
	public void testEmpezarTarea() {
		AppRRHH app = new AppRRHH();
		app.agregarEmpleadoContratado(1, "Yamil", 1000D);
		Boolean rta = false;
		try {
			app.asignarTarea(1, 1, "t1", 20);
			app.empezarTarea(1, 1);
			if (app.getEmpleados().get(0).getTareasAsignadas().get(0).getFechaInicio() != null) {
				rta = true;
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		assertTrue(rta);
	}
	
	@Test
	public void testTerminarTarea() {
		AppRRHH app = new AppRRHH();
		app.agregarEmpleadoContratado(1, "Yamil", 1000D);
		Boolean rta = false;
		try {
			app.asignarTarea(1, 1, "t1", 20);
			app.terminarTarea(1, 1);
			if (app.getEmpleados().get(0).getTareasAsignadas().get(0).getFechaFin() != null) {
				rta = true;
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		assertTrue(rta);
	}
	
	@Test
	public void testCargarEmpleadosContratados() {
		AppRRHH app = new AppRRHH();
		app.cargarEmpleadosContratadosCSV("empleadosContratados.csv");
		List<Empleado> listaEmpleados= new ArrayList<Empleado>();
		listaEmpleados.add(new Empleado(1,"Yamil",Tipo.CONTRATADO,1000D));
		listaEmpleados.add(new Empleado(2,"Arturo",Tipo.CONTRATADO,2000D));
		listaEmpleados.add(new Empleado(3,"Rafart",Tipo.CONTRATADO,4000D));
		assertTrue(app.empleadosIgualA(listaEmpleados));
	}
	
	@Test
	public void testCargarEmpleadosEfectivos() {
		AppRRHH app = new AppRRHH();
		app.cargarEmpleadosEfectivosCSV("empleadosEfectivos.csv");
		List<Empleado> listaEmpleados= new ArrayList<Empleado>();
		listaEmpleados.add(new Empleado(1,"Yamil",Tipo.EFECTIVO,1000D));
		listaEmpleados.add(new Empleado(2,"Arturo",Tipo.EFECTIVO,2000D));
		listaEmpleados.add(new Empleado(3,"Rafart",Tipo.EFECTIVO,4000D));
		assertTrue(app.empleadosIgualA(listaEmpleados));
	}
	
}
