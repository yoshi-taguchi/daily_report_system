package controllers.follow;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Employee_Follow;
import utils.DBUtil;

/**
 * Servlet implementation class FollowDestroyServlet
 */
@WebServlet("/follow/destroy")
public class FollowDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        //フォローされる側の従業員情報を取得
        Employee e = em.find(Employee.class, Integer.parseInt(request.getParameter("id")));
        //フォローする側（ログイン中の従業員情報を取得
        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");

        try{
            Employee_Follow ef = em.createNamedQuery("getOneFollow",Employee_Follow.class)
                                                .setParameter("employee", login_employee)
                                                .setParameter("follow", e)
                                                .getSingleResult();
            em.getTransaction().begin();
            em.remove(ef);
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "フォローを解除しました。");
            response.sendRedirect(request.getContextPath() + "/follow/index");
        }
        catch(NoResultException er){
            em.close();
            request.getSession().setAttribute("flush", "この従業員はフォローしてません");
            response.sendRedirect(request.getContextPath() + "/follow/index");
        }
    }

}
