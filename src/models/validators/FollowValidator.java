package models.validators;


import javax.persistence.EntityManager;

import models.Employee;
import utils.DBUtil;

public class FollowValidator {
        public static String validateFollow(Employee employee, Employee follow,Boolean follow_duplicate_check_flag) {
        //既に登録されているフォローIDとの重複チェック
        if(follow_duplicate_check_flag) {
            EntityManager em = DBUtil.createEntityManager();
            long follow_check = (long)em.createNamedQuery("checkFollowCombination", Long.class)
                                           .setParameter("employee", employee)
                                           .setParameter("follow", follow)
                                           .getSingleResult();
            em.close();
            if(follow_check > 0) {
                return "既にフォローしています。";
            }
        }return "";
    }
}
