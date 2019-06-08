package controllers.follow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Employee_Follow;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class FollowReportsIndex
 */
@WebServlet("/follow/reports/index")
public class FollowReportsIndex extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowReportsIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");

        List<Employee_Follow> ef = em.createNamedQuery("getAllMyFollows", Employee_Follow.class)
                .setParameter("employee",login_employee )
                .getResultList();

        List<Report> reports = new ArrayList<Report>();

        long reports_count = (long)em.createNamedQuery("getReportsCount", Long.class)
                .getSingleResult();

        for(int i = 0; i <= reports_count; i++){
            try{
                Employee a = ef.get(i).getFollow();
                List<Report> r = em.createNamedQuery("getMyAllReports", Report.class)
                            .setParameter("employee", a)
                            .getResultList();
                reports.addAll(r);
                } catch(NoResultException er){
                    System.out.println("日報を登録していません");
                } catch(IndexOutOfBoundsException ei){
                    System.out.println("フォローの日報を取得しきりました");
                }

            }

        em.close();

        request.setAttribute("reports", reports);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/follows/reports.jsp");
        rd.forward(request, response);
    }

}
