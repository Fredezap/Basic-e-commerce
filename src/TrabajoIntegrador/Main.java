package TrabajoIntegrador;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class Main {
	
	private static void menuPrincipal() {
		int Opcion = 0;
		Scanner dato = new Scanner(System.in);
		boolean continuar = true;
		while(continuar) {
	        System.out.println("\n Por favor, escoja una opción.");
	        System.out.println("--------------------------------");
	        System.out.println("1) CLIENTES");
	        System.out.println("2) PRODUCTOS");
	        System.out.println("3) NUEVA COMPRA");
	        System.out.println("4) SALIR");
	        System.out.println("--------------------------------");
	        try {
	        	Opcion = dato.nextInt();
	        } catch (InputMismatchException e) {
		        System.out.println("Ocurrio un error. Ingrese solo numeros");
	        	dato.nextLine();
		        continue;
	        }
			switch (Opcion) {
			  case 1:
				Case1();
				break;
			 case 2: 
				Case2();
				break;
			 case 3:
				Case3();
				break;
			 case 4: 
				System.out.println("Has salido del programa\nNos vemos pronto");
				continuar = false;
				break;
			 default: 
				System.out.println("Opcion incorrecta, intente nuevamente\n");
			}
		}
	}
	
	public static void Case1() {
		Scanner dato = new Scanner(System.in);
		int OpcionCase1 = 0;
		boolean continuarCase1 = true;
	    while (continuarCase1) {
			Cliente c = new Cliente(0,"","","",0,"");
	        System.out.println("\n Por favor, escoja una opción.");
	        System.out.println("--------------------------------");
	        System.out.println("1) CONSULTAR CLIENTES");
	        System.out.println("2) AGREGAR CLIENTE");
	        System.out.println("3) MODIFICAR CLIENTE");
	        System.out.println("4) ELIMINAR CLIENTE");
	        System.out.println("5) SALIR");
	        System.out.println("--------------------------------");
	        try {
			    OpcionCase1 = dato.nextInt();
	        } catch (InputMismatchException e) {
		        System.out.println("Ocurrio un error. Ingrese solo numeros");
	        	dato.nextLine();
		        continue;
	        }
		    if (OpcionCase1 == 1) {
		    	c.ConsultarClientes();
		    } else if (OpcionCase1 == 2) {
		    	c.AgregarCliente();
		    } else if (OpcionCase1 == 3) {
		    	c.ModificarCliente();
		    } else if (OpcionCase1 == 4) {
		    	c.EliminarCliente();
		    } else if (OpcionCase1 == 5) {
		    	continuarCase1 = false;
		    	break;
		    } else {
		    	System.out.println("Opcion incorrecta");	
		    }
	    }
	}
	
	public static void Case2() {
		Scanner dato = new Scanner(System.in);
		int OpcionCase2 = 0;
		boolean continuarCase2 = true;
	    while (continuarCase2) {
	    	Producto p = new Producto("",0,"",0,0);
	        System.out.println("\n Por favor, escoja una opción.");
	        System.out.println("--------------------------------");
	        System.out.println("1) CONSULTAR PRODUCTOS");
	        System.out.println("2) AGREGAR PRODUCTO");
	        System.out.println("3) MODIFICAR PRODUCTO");
	        System.out.println("4) ELIMINAR PRODUCTO");
	        System.out.println("5) SALIR");
	        System.out.println("--------------------------------");
	        try {
			    OpcionCase2 = dato.nextInt();
	        } catch (InputMismatchException e) {
		        System.out.println("Ocurrio un error. Ingrese solo numeros");
	        	dato.nextLine();
		        continue;
	        }
		    if (OpcionCase2 == 1) {
		    	p.ConsultarProductos();
		    } else if (OpcionCase2 == 2) {
		    	p.AgregarProducto();
		    } else if (OpcionCase2 == 3) {
		    	p.ModificarProducto();
		    } else if (OpcionCase2 == 4) {
		    	p.EliminarProducto();
		    } else if (OpcionCase2 == 5) {
		    	continuarCase2 = false;
		    	break;
		    } else {
		    	System.out.println("Opcion incorrecta");
	    	}
		}
	}
	
		public static void Case3() {
	    Ticket ticket = new Ticket("","");
		List<Number> Productos = new ArrayList<>();
		Cliente c = new Cliente(0,"","","",0,"");
    	Producto p = new Producto("",0,"",0,0);
		Scanner dato = new Scanner(System.in);
		boolean continuarCase3 = true;   	
		boolean continuarProductos = true;
		boolean continuarRes = true;
		boolean continuarVerifExist = true;
		int i;
		String ClienteNombre = "";
		String ClienteApellido = "";
		int ClienteDni = 0;
		int Dni = 0;
		int CheckID = 0;
		boolean CheckInt = true;
		boolean CheckFloat = true;
		int ID = 0;
		float cantidad = 0;
		
		while (continuarCase3) {
	    	while (continuarVerifExist) {
	    		CheckInt = true;
	    		while (CheckInt) {
	    		try {
		    		System.out.println("Dni del cliente que va a realizar la compra: \n");
			    	Dni = dato.nextInt();
			    	CheckInt = false;
			    	break;
	    		} catch (Exception e){
					System.out.println("Ocurrio un error, asegurese que esta ingresando un numero\n");
					dato.nextLine();
	    		}
	    	}
	    	try {
		    	ResultSet verificarExistencia = c.SelectOne(Dni);
		    	if (verificarExistencia.next()) {
			    	ClienteDni = verificarExistencia.getInt("ClienteDni");
			    	ClienteNombre = verificarExistencia.getString("ClienteNombre");
			    	ClienteApellido = verificarExistencia.getString("ClienteApellido");
					continuarVerifExist = false;
					break;
		    	} else {
				System.out.println("El cliente no existe\n");
				}
	    	} catch (Exception e) {
	    	System.out.println(e.getMessage());
	    	}
	    	}
	    	
	    	p.ConsultarProductos();
	    	System.out.println("\nElija los productos a comprar y su cantidad");
	    	System.out.println("-------------------------------------------- \n");
	    	continuarProductos:
	    	while (continuarProductos) {
	    		continuarRes = true;
	    		CheckInt = true;
	    		while (CheckInt) {
	    		try {
			    	System.out.println("Producto ID:");
			    	ID = dato.nextInt();
			    	CheckInt = false;
			    	break;
	    		} catch (Exception e){
					System.out.println("Ocurrio un error, asegurese que esta ingresando un numero\n");
					dato.nextLine();
	    		}
	    	}
	    		System.out.println(ID);
		    	boolean checkExiste = p.CheckExiste(ID); //	CHECKEO PARA VER SI EXISTE EL PRODUCTO EN LA BASE DE DATOS
		    	if (checkExiste) {
		    		if (Productos.size()==0) { //ESTE CHEQUEO ES EL MISMO QUE EL DE ABAJO, PERO LO AGREGUE PARA CUANDO LA LISTA ESTA VACIA
				    	CheckFloat = true;
		    			while (CheckFloat) {
				    		try {
						    	System.out.println("Cantidad:");
						    	cantidad = dato.nextFloat();
						    	CheckFloat = false;
						    	break;
				    		} catch (Exception e){
								System.out.println("Ocurrio un error, asegurese que esta ingresando un numero\n");
								dato.nextLine();
				    		}
				    	}

				    	boolean checkStock = p.CheckStock(cantidad,ID); // CHECKEO DE STOCK EN BASE DE DATOS
				    	if (checkStock) {	
					    	Productos.addAll(Arrays.asList(ID,cantidad)); // SE HACE UN ADD A UNA LISTA DEL ID DEL PRODUCTO Y DE LA CANTIDAD SELECCIONADA PARA INGRESARLOS A LA BASE DE DATOS LUEGO
				    	} else {
					    	System.out.println("No hay stock suficiente22/n");
					    	continue;
				    	}
		    		} else {
				    	for (i=0;i<Productos.size();i++) { // CHECKEO PARA QUE NO PUEDA SELECCIONAR EL MISMO PRODUCTO (POR CUESTION DE STOCK)
				    		Number CheckID1 = Productos.get(i);
			    			CheckID = CheckID1.intValue();
			    			i += 1;
				    		if (CheckID == ID) {
				    	    	System.out.println("Ya has seleccionado ese producto");
				    	    	continue continuarProductos;
				    		} 
				    	}
				    	CheckFloat = true;
			    		while (CheckFloat) {
				    		try {
						    	System.out.println("Cantidad:");
						    	cantidad = dato.nextFloat();
						    	CheckFloat = false;
						    	break;
				    		} catch (Exception e){
								System.out.println("Ocurrio un error, asegurese que esta ingresando un numero\n");
								dato.nextLine();
				    		}
				    	}
				    	boolean checkStock = p.CheckStock(cantidad,ID); // CHECKEO DE STOCK EN BASE DE DATOS
				    	if (checkStock) {	
					    	Productos.addAll(Arrays.asList(ID,cantidad)); // SE HACE UN ADD A UNA LISTA DEL ID DEL PRODUCTO Y DE LA CANTIDAD SELECCIONADA PARA INGRESARLOS A LA BASE DE DATOS LUEGO
				    	} else {
					    	System.out.println("No hay stock suficiente/n");
					    	continue;
			    		}
				    } 
		    	} else {
			    	    	System.out.println("El producto seleccionado no existe");
			    	    	continue;
				    	}
		    	
		    	while(continuarRes) {
			    	System.out.println("\nDesea agregar un producto mas? s/n");
			    	String res = dato.next();
			    	if (res.equals("n") | res.equals("N")) {
				    	System.out.println("\nFinalizar compra? s/n");
				    	String res2 = dato.next();
				    	if (res2.equals("s") | res2.equals("S")){
				    		continuarProductos = false;
				    		break;
				    	} else if (res.equals("n") | res.equals("N")) {
				    		continuarRes = false;
				    		break;
				    	} else {
					    	System.out.println("\nOpcion incorrecta/n");
					    	}
			    	} else if (res.equals("s") | res.equals("S")) {
			    		continuarRes = false;
			    		break;
			    	} else {
				    	System.out.println("\nOpcion incorrecta/n");
				    	}
			    	}
		    	}

	        LocalDateTime fechaHora = LocalDateTime.now();
	        String Fecha = fechaHora.getYear() + "-" + fechaHora.getMonthValue() + "-" + fechaHora.getDayOfMonth();
	        String Hora = fechaHora.getHour() + ":" + fechaHora.getMinute() + ":" + fechaHora.getSecond();
	        ticket = new Ticket(Fecha,Hora);
	    	ticket.CrearTicket(ClienteDni,Fecha,Hora);
	    	int TicketID = ticket.GetTicketID(ClienteDni,Fecha,Hora);
	    		for (i=0;i<Productos.size();i++) {
	    			Number ProdID1 = Productos.get(i);
	    			i += 1;
	    			Number Cantidad1 = Productos.get(i);
	    			int ProdID = ProdID1.intValue();
	    			float Cantidad = Cantidad1.floatValue();
	    			ticket.InsertarProductosTicketDetalle(ProdID,TicketID,Cantidad);
	    		}
	    		
	    		// Imprime el ticket antes de salir del metodo
	    		ticket.ImprimirTicket(TicketID, ClienteNombre, ClienteApellido);		
			continuarCase3 = false;
			break;
		}
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.menuPrincipal();
	}
}





