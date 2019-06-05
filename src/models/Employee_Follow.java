package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="employee_follow",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"employee_id", "follow_id"})})

@NamedQueries({
    @NamedQuery(
            name="getAllMyFollows",
            query="SELECT ef FROM Employee_Follow AS ef WHERE ef.employee = :employee ORDER BY ef.id DESC"
            ),
    @NamedQuery(
            name = "checkFollowCombination",
            query = "SELECT COUNT(ef) FROM Employee_Follow AS ef WHERE ef.employee = :employee AND ef.follow = :follow"
            )
})
public class Employee_Follow {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="follow_id", nullable=false)
    private Employee follow;

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public Employee getEmployee(){
        return employee;
    }
    public void setEmployee(Employee employee){
        this.employee = employee;
    }
    public Employee getFollow(){
        return follow;
    }
    public void setFollow(Employee follow){
        this.follow = follow;
    }
}
