package com.example.passwordencoding.repository;

import com.example.passwordencoding.model.Company;
import com.example.passwordencoding.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    public List<Employee>getEmployeeNameByCompanyName(String companyName){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);

            // Define a join with the Company entity assuming there's a relationship between Employee and Company
            Join<Employee, Company> employeeCompanyJoin = root.join("company",JoinType.INNER);

            // Define a parameter for the company name
            ParameterExpression<String> companyNameParam = criteriaBuilder.parameter(String.class);

            // Add conditions to your predicate list as needed
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(employeeCompanyJoin.get("companyName"), companyNameParam));

            criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));

            TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);
            query.setParameter(companyNameParam, companyName);

            List<Employee> resultList = query.getResultList();
            return resultList;
        } finally {
            entityManager.close();
        }
    }

    public List<Employee> getEmployeeNameByEmployeeId(Long employeeId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);

            // Define a parameter for the employeeId
            ParameterExpression<Long> employeeIdParam = criteriaBuilder.parameter(Long.class);

            // Add a condition to your predicate list to filter by employeeId
            //Predicate condition = criteriaBuilder.equal(root.get("employeeId"), employeeIdParam);

            criteriaQuery.select(root);
            //.where(condition);

            TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);
            query.setParameter(employeeIdParam, employeeId);

            List<Employee> resultList = query.getResultList();
            return resultList;
        } finally {
            entityManager.close();
        }
    }
    public List<Employee> searchEmployee(String userName) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);

          //  Expression<String> lower = criteriaBuilder.concat(criteriaBuilder.lower(root.get("employeeName")),criteriaBuilder.lower(root.get("employeeLastName")));


        // Concatenate the employeeName and employeeLastName fields and make it lowercase
          /*  Expression<String> fullName = criteriaBuilder.concat(root.get("employeeName"),
                    criteriaBuilder.concat(" ",root.get("employeeLastName")));


           */
            criteriaQuery.select(root);
            List<Predicate> predicates = new ArrayList<>();

            if (userName != null && !userName.isEmpty()) {
                // Create a case-insensitive like predicate for the full name
                String userNamePattern="%"+userName.toLowerCase()+"%";
                Predicate fullName= criteriaBuilder.like(criteriaBuilder.concat(criteriaBuilder.concat(criteriaBuilder.lower(root.get("employeeName")),criteriaBuilder.literal(" ")    ),
                         criteriaBuilder.lower(root.get("employeeLastName"))),userNamePattern);

               // Predicate fullNameLike = cb.like(cb.concat( cb.concat(cb.lower(root.get("firstName")),cb.literal(" ")),
                //        cb.lower(root.get("lastName"))),employeeNamePattern);


               // Predicate userPredicate = criteriaBuilder.like(fullName, "%" + userName.toLowerCase() + "%");
                predicates.add(fullName);
            }

            // Combine all predicates with 'AND'
            if (!predicates.isEmpty()) {
                criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            }

            TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);
            List<Employee> queryResultList = query.getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();
            return queryResultList;

    }
}
