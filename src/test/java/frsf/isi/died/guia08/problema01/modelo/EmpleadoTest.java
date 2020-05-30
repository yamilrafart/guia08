package frsf.isi.died.guia08.problema01.modelo;

import static org.junit.Assert.*;


import org.junit.Test;

import frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo;


public class EmpleadoTest {
	
	@Test
	public void testSalarioContratado() {
		Empleado e1 = new Empleado(1,"Yamil",Tipo.CONTRATADO,1000D);
		try {
			Tarea t1 = new Tarea(1,"t1",20);
			e1.asignarTarea(t1);
			e1.comenzar(1,"24-05-2020 18:00");
			e1.finalizar(1,"28-05-2020 18:00");
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}	
		Double resultadoEsperado = 20800D;
		Double resultadoReal = e1.salario();
		assertEquals(resultadoEsperado,resultadoReal);
	}
	
	@Test
	public void testSalarioEfectivo() {
		Empleado e1 = new Empleado(1,"Yamil",Tipo.EFECTIVO,1000D);
		try {
			Tarea t1 = new Tarea(1,"t1",20);
			e1.asignarTarea(t1);
			e1.comenzar(1,"24-05-2020 18:00");
			e1.finalizar(1,"28-05-2020 18:00");
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		} 
		Double resultadoEsperado = 19200D;
		Double resultadoReal = e1.salario();
		assertEquals(resultadoEsperado,resultadoReal);
	}

	@Test
	public void testCostoTareaFinalizadaContratado() {
		Empleado e1 = new Empleado(1,"Yamil",Tipo.CONTRATADO,1000D);
		Tarea t1 = new Tarea(1,"t1",20);
		try {
			e1.asignarTarea(t1);
			e1.comenzar(1,"24-05-2020 18:00");
			e1.finalizar(1,"28-05-2020 18:00");
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		Double resultadoEsperado = 20800D;
		Double resultadoReal = e1.costoTarea(t1);
		assertEquals(resultadoEsperado,resultadoReal);
	}
	
	@Test
	public void testCostoTareaNoFinalizadaContratado() {
		Empleado e1 = new Empleado(1,"Yamil",Tipo.CONTRATADO,1000D);
		Tarea t1 = new Tarea(1,"t1",20);
		try {	
			e1.asignarTarea(t1);
			e1.comenzar(1,"24-05-2020 18:00");
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		Double resultadoEsperado = 20000D;
		Double resultadoReal = e1.costoTarea(t1);
		assertEquals(resultadoEsperado,resultadoReal);
	}
	
	@Test
	public void testCostoTareaFinalizadaEfectivo() {
		Empleado e1 = new Empleado(1,"Yamil",Tipo.EFECTIVO,1000D);
		Tarea t1 = new Tarea(1,"t1",20);
		try {	
			e1.asignarTarea(t1);
			e1.comenzar(1,"24-05-2020 18:00");
			e1.finalizar(1,"28-05-2020 18:00");
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		} 
		Double resultadoEsperado = 19200D;
		Double resultadoReal = e1.costoTarea(t1);
		assertEquals(resultadoEsperado,resultadoReal);
	}
	
	@Test
	public void testCostoTareaNoFinalizadaEfectivo() {
		Empleado e1 = new Empleado(1,"Yamil",Tipo.EFECTIVO,1000D);
		Tarea t1 = new Tarea(1,"t1",20);
		try {	
			e1.asignarTarea(t1);
			e1.comenzar(1,"24-05-2020 18:00");
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		Double resultadoEsperado = 20000D;
		Double resultadoReal = e1.costoTarea(t1);
		assertEquals(resultadoEsperado,resultadoReal);
	}

	@Test (expected = AsignacionTareaException.class)
	public void testAsignarTareaFalla() throws Exception {
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
	public void testAsignarTareaExito() {
		Empleado e1 = new Empleado(1,"Yamil",Tipo.EFECTIVO,1000D);
		Tarea t1 = new Tarea(1,"t1",20);
		try {	
			e1.asignarTarea(t1);
			e1.comenzar(1,"24-05-2020 18:00");
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		assertTrue(e1.getTareasAsignadas().contains(t1));
	}
	
	@Test
	public void testAsignarTareaFallaContratado() {
		Empleado e1 = new Empleado(1,"Yamil",Tipo.CONTRATADO,1000D);
		Tarea t1 = new Tarea(1,"t1",20);
		Tarea t2 = new Tarea(2,"t2",20);
		Tarea t3 = new Tarea(3,"t3",20);
		Tarea t4 = new Tarea(4,"t4",20);
		Tarea t5 = new Tarea(5,"t5",20);
		Tarea t6 = new Tarea(6,"t6",20);
		Tarea t7 = new Tarea(7,"t7",20);
		Boolean rta = true;
		try {	
			e1.asignarTarea(t1);
			e1.asignarTarea(t2);
			e1.asignarTarea(t3);
			e1.asignarTarea(t4);
			e1.asignarTarea(t5);
			e1.asignarTarea(t6);
			rta=e1.asignarTarea(t7);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		assertFalse(rta);
	}
	
	@Test
	public void testAsignarTareaFallaEfectivo() {
		Empleado e1 = new Empleado(1,"Yamil",Tipo.EFECTIVO,1000D);
		Tarea t1 = new Tarea(1,"t1",20);
		Tarea t2 = new Tarea(2,"t2",20);
		Boolean rta = true;
		try {	
			e1.asignarTarea(t1);
			rta=e1.asignarTarea(t2);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		assertFalse(rta);
	}

	@Test
	public void testComenzarIntegerExito() {
		Empleado e1 = new Empleado(1,"Yamil",Tipo.CONTRATADO,1000D);
		Tarea t1 = new Tarea(1,"t1",20);
		try {
			e1.asignarTarea(t1);
			e1.comenzar(t1.getId());
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		assertNotNull(t1.getFechaInicio());
	}
	
	@Test (expected = TareaInexistenteException.class)
	public void testComenzarIntegerFalla() throws TareaInexistenteException {
		Empleado e1 = new Empleado(1,"Yamil",Tipo.CONTRATADO,1000D);
		e1.comenzar(1);
	}
	
	@Test
	public void testFinalizarIntegerExito() {
		Empleado e1 = new Empleado(1,"Yamil",Tipo.CONTRATADO,1000D);
		Tarea t1 = new Tarea(1,"t1",20);
		try {
			e1.asignarTarea(t1);
			e1.finalizar(t1.getId());
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		assertNotNull(t1.getFechaFin());
	}
	
	@Test (expected = TareaInexistenteException.class)
	public void testFinalizarIntegerFalla() throws TareaInexistenteException {
		Empleado e1 = new Empleado(1,"Yamil",Tipo.CONTRATADO,1000D);
		e1.finalizar(1);
	}

	@Test
	public void testComenzarIntegerStringExito() {
		Empleado e1 = new Empleado(1,"Yamil",Tipo.CONTRATADO,1000D);
		Tarea t1 = new Tarea(1,"t1",20);
		try {
			e1.asignarTarea(t1);
			e1.comenzar(t1.getId(),"20-05-2020 08:00"); 
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		assertNotNull(t1.getFechaInicio());
	}
	
	@Test (expected = TareaInexistenteException.class)
	public void testComenzarIntegerStringFalla() throws TareaInexistenteException {
		Empleado e1 = new Empleado(1,"Yamil",Tipo.CONTRATADO,1000D);
		e1.comenzar(1,"20-05-2020 08:00"); 
	}

	@Test
	public void testFinalizarIntegerStringExito() {
		Empleado e1 = new Empleado(1,"Yamil",Tipo.CONTRATADO,1000D);
		Tarea t1 = new Tarea(1,"t1",20);
		try {
			e1.asignarTarea(t1);
			e1.finalizar(t1.getId(),"20-05-2020 08:00"); 
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		assertNotNull(t1.getFechaFin());
	}
	
	@Test (expected = TareaInexistenteException.class)
	public void testFinalizarIntegerStringFalla() throws TareaInexistenteException {
		Empleado e1 = new Empleado(1,"Yamil",Tipo.CONTRATADO,1000D);
		e1.finalizar(1,"20-05-2020 08:00");
	}

}
