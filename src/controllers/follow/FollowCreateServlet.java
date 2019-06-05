package controllers.follow;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Employee_Follow;
import models.validators.FollowValidator;
import utils.DBUtil;

/**
 * Servlet implementation class FollowCreateServlet
 */
@WebServlet("/follow/create")
public class FollowCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    EntityManager em = DBUtil.createEntityManager();

    Employee_Follow ef = new Employee_Follow();
    ef.setEmployee((Employee)request.getSession().getAttribute("login_employee"));
    //バリデーション用に呼び出し
    Employee employee = ef.getEmployee();

    Employee follow = em.find(Employee.class, Integer.parseInt(request.getParameter("id")));
    ef.setFollow(follow);

    String error = FollowValidator.validateFollow(employee,follow,true);
        if(error.equals("")){
            em.getTransaction().begin();
            em.persist(ef);
            em.getTransaction().commit();

            em.close();
            request.getSession().setAttribute("flush", "従業員をフォローしました");

            response.sendRedirect(request.getContextPath() + "/reports/index");
        } else{
            em.close();
            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("employee", employee);
            request.setAttribute("errors", error);
            request.getSession().setAttribute("flush", "既にフォローしています");

            response.sendRedirect(request.getContextPath() + "/reports/index");

            }

    }

}
