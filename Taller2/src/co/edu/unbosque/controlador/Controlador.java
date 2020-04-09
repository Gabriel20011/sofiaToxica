package co.edu.unbosque.controlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.unbosque.model.Usuario;

/**
 * Servlet implementation class Controlador
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ArrayList<Usuario> usuarios;
	private DTO DTO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controlador() {
		super();
		usuarios = new ArrayList<Usuario>();
		DTO = new DTO();
		DTO.cargarUsuarios(usuarios);



		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		request.setAttribute("Nombre_usuario", DTO.noRepetidosYOrdenadosNombre(usuarios));
		request.setAttribute("Apellido_usuario", DTO.noRepetidosYOrdenadosApellido(usuarios));
		request.setAttribute("Edad_usuario", DTO.noRepetidosYOrdenadosEdad(usuarios));
		request.setAttribute("Genero_usuario", DTO.noRepetidosYOrdenadosGenero(usuarios));
		request.setAttribute("Pais_usuario", DTO.noRepetidosYOrdenadosPais(usuarios));
		request.setAttribute("Ciudad_usuario", DTO.noRepetidosYOrdenadosCiudad(usuarios));
		request.setAttribute("numeroresultado", usuarios.size());
		RequestDispatcher miDis = request.getRequestDispatcher("/sofia.jsp");
		
		miDis.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Usuario> filtro;
		
		String respuesta = "Filtrado para:"+" "+request.getParameter("Nombre_usuario")+" "+request.getParameter("Apellido_usuario")+" "+request.getParameter("Edad_usuario")
		+" "+request.getParameter("Genero_usuario")+" "+request.getParameter("Pais_usuario")+" "+request.getParameter("Ciudad_usuario");
		
		
		
		request.setAttribute("NCOLUMNAS", o);
		request.setAttribute("respuestra", respuesta);
		request.setAttribute("Nombre_usuario", request.getParameter("Nombre_usuario"));
		
		RequestDispatcher rd = request.getRequestDispatcher("gabox.jsp");
		rd.forward(request, response);

		 
//		p.print("<h1>"+respuesta+"</h1>     ");
//		p.print("<table>     ");
//		for (Usuario i : usuarios) {
//			p.print("<tr>     ");
//			p.print("<td>"+i.getNombre()+"</td>");
//			p.print("<td>"+i.getApellido()+"</td>");
//			p.print("<td>"+i.getEdad()+"</td>");
//			p.print("<td>"+i.getGenero()+"</td>");
//			p.print("<td>"+i.getPais()+"</td>");
//			p.print("<td>"+i.getCiudad()+"</td>");
//			p.print("</tr>     ");
//		}
//		p.print("</table>     ");
//		p.print("</body>     ");
//		p.print("</html>     ");
	}

	public ArrayList<Usuario> filtrar(String nombre, String apellido, 
			String edad, String genero, String pais, String ciudad) {
		ArrayList<Usuario> ahPerro = new ArrayList<Usuario>();
		for (Usuario i : usuarios) {
			if (i.getNombre().equals(nombre) || nombre == null) {
				if (i.getApellido().equals(apellido) || apellido == null) {
					if (i.getEdad().equals(edad) || edad.equals("0")) {
						if (i.getGenero().equals(genero) || genero.equals("0")) {
							if (i.getPais().equals(pais) || pais == null ) {
								if (i.getCiudad().equals(ciudad) || ciudad == null) {
									ahPerro.add(i);
								}
							}
						}
					}
				}
			}
		}
		return ahPerro;
	}
}