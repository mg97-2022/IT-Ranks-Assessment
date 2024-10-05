package com.it_ranks.employee.dao;

import com.it_ranks.employee.entity.Branch;
import com.it_ranks.employee.exception.NotFoundException;
import com.it_ranks.employee.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class BranchDAO {
    public Branch getBranchById(int id) {
        Session session = HibernateUtil.getSession();
        Branch branch = null;

        try {
            branch = session.get(Branch.class, id);
        } finally {
            session.close();
        }

        if (branch == null) {
            throw new NotFoundException("Branch", id);
        }
        return branch;
    }
}
