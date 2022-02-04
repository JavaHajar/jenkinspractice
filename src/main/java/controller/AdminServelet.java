package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import dao.UserDao;
import dao.UserDaoImp;
import entities.Role;
import entities.User;
import repository.AdminRepos;
import repository.AdminReposImp;
import thymeleaf.TemplateEngineUtil;

/**
 * Servlet implementation class AdminServelet
 */
@WebServlet("/")
public class AdminServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDao ;
	AdminRepos adminRepo;
	TemplateEngine engine;
	WebContext context;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServelet() {
        this.userDao = new UserDaoImp();
        this.adminRepo = new AdminReposImp();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        response.setCharacterEncoding("utf-8");
        engine.process("AddUser.html", context, response.getWriter());*/
		
		String action = request.getServletPath();

        switch (action) {
        case "/new":
            showNewForm(request, response);
            break;

        case "/insert":
            insertUser(request, response);
            break;

        case "/delete":
            deleteUser(request, response);
            break;

        case "/edit":
            showEditForm(request, response);
            break;

        case "/update":
            updateUser(request, response);
            break;
        case "/login":
        	try {
              authenticate(request, response);
          } catch (Exception e) {
              e.printStackTrace();
              response.setContentType("text/html");
              PrintWriter out = response.getWriter();
              out.println("Connexion echouée! Email ou mot de passe incorrect ");
              showlogin(request, response);
          }
            break;
        case "/logout":
        	response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("Merci!, votre session a été supprimée avec succés!");
            HttpSession session = request.getSession(false);
            // session.setAttribute("user", null);
           // session.removeAttribute("userr");
           // session.getMaxInactiveInterval();
            showlogin(request, response);
            break;
        default:
        	
        	showlogin(request, response);
            break;
        }
    }
	
	public void  showlogin(HttpServletRequest request, HttpServletResponse response) {
		this.engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        this.context = new WebContext(request, response, request.getServletContext());
        response.setCharacterEncoding("utf-8"); 
        try {
			engine.process("login.html", context, response.getWriter());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
     private void authenticate(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (adminRepo.validate(email, password)) {
            	listUser(request, response);
            } else {
            	
                throw new Exception("Login not successful..");
            }
        }
    @SuppressWarnings("unused")
	private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        List<User> Allusers = userDao.findAll();
       // Allusers.forEach(s->System.out.println(s));
        this.engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        this.context = new WebContext(request, response, request.getServletContext());
        response.setCharacterEncoding("utf-8");
        context.setVariable("users",Allusers);
        engine.process("Home.html", context, response.getWriter());
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        this.context = new WebContext(request, response, request.getServletContext());
        response.setCharacterEncoding("utf-8");
        engine.process("AddUser.html", context, response.getWriter());
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        this.context = new WebContext(request, response, request.getServletContext());
        response.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDao.findById(id);
        context.setVariable("edituser", existingUser);
        engine.process("EditUser.html", context, response.getWriter());
    }
    
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");       
        String role = request.getParameter("role"); 
        User user = new User(id,nom,prenom,email,password);
        Role rolee = new Role(role);        
        user.setRole(rolee);        
          
        userDao.update(user);
        listUser(request, response);
             
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        this.context = new WebContext(request, response, request.getServletContext());
        response.setCharacterEncoding("utf-8");      
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");       
        String role = request.getParameter("role");       
        User user = new User(nom,prenom,email,password);
        Role rolee = new Role(role);        
        user.setRole(rolee);  
         
        try {
        	userDao.save(user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        context.setVariable("name", nom);
        listUser(request, response);
    }
    
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        userDao.delete(id);
        listUser(request, response);
    }
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*UserDao userDao = new UserDaoImp();
		Role role = new Role("Admin");
		User user = new User( "Elkhatabi", "aimad", "aimad@gmail.com", "aimadaimad");
		user.setRole(role);
		userDao.save(user);*/
		
		/*UserDao userDao = new UserDaoImp();
		TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        response.setCharacterEncoding("utf-8");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");       
        String role = request.getParameter("role"); 
        User user = new User(nom,prenom,email,password);
        Role rolee = new Role(role);        
        user.setRole(rolee);        
        try {
        	userDao.save(user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/templates/Success.html");
        dispatcher.forward(request, response);
        //response.sendRedirect("");		
	}
	*/
		this.doGet(request, response);
	}
		
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
