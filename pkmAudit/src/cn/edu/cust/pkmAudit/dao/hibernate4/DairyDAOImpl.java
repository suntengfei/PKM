package cn.edu.cust.pkmAudit.dao.hibernate4;

import org.springframework.stereotype.Repository;

import cn.edu.cust.common.dao.BaseDAOImpl;
import cn.edu.cust.pkmAudit.dao.IDairyDAO;
import cn.edu.cust.pkmAudit.model.Dairy;

@Repository("dairyDAO")
public class DairyDAOImpl extends BaseDAOImpl<Dairy, String> implements IDairyDAO {

}
