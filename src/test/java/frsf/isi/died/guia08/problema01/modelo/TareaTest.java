package frsf.isi.died.guia08.problema01.modelo;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo;

public class TareaTest {

	@Test
	public void testAsignarEmpleadoTestExito() {
		Empleado e1 = new Empleado(1,"Yamil",Tipo.CONTRATADO,1000D);
		Tarea t1 = new Tarea(1,"t1",20);
		try {	
			t1.asignarEmpleado(e1);
		}catch(Exception exception) {
			System.out.println(exception.getMessage());
		}
		assertEquals(e1,t1.getEmpleadoAsignado());
	}
	
	@Test (expected = AsignacionTareaException.class)
	public void testAignarEmpleadoTestFalla() throws Exception{
		Empleado e1 = new Empleado(1,"Yamil",Tipo.CONTRATADO,1000D);
		Empleado e2 = new Empleado(2,"Rafart",Tipo.CONTRATADO,1000D);
		Tarea t1 = new Tarea(1,"t1",20);
		try {	
			t1.asignarEmpleado(e1);
		}catch(Exception exception) {
			System.out.println(exception.getMessage());
		}
		t1.asignarEmpleado(e2);
	}
	
	@Test
	public void testFinalizaAntes() {
		Tarea t1 = new Tarea(1,"t1",20);
		DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		t1.setFechaInicio(LocalDateTime.parse("24-05-2020 18:00",formato));
		t1.setFechaFin(LocalDateTime.parse("28-05-2020 18:00",formato));
		assertTrue(t1.finalizaAntes());
	}
	
	@Test
	public void testFinalizaDespues() {
		Tarea t1 = new Tarea(1,"t1",20);
		DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		t1.setFechaInicio(LocalDateTime.parse("23-05-2020 18:00",formato));
		t1.setFechaFin(LocalDateTime.parse("31-05-2020 18:00",formato));
		assertTrue(t1.finalizaDespues());
	}

}
