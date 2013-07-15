package cn.edu.cust.pkmAudit.service.impl;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.sql.DataSource;

import org.apache.naming.ContextBindings;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cn.edu.cust.common.dao.IBaseDAO;
import cn.edu.cust.common.service.BaseServiceImpl;
import cn.edu.cust.pkmAudit.dao.IDairyDAO;
import cn.edu.cust.pkmAudit.model.Dairy;
import cn.edu.cust.pkmAudit.service.IDairyService;

@Service("dairyService")
public class DairyServiceImpl extends BaseServiceImpl<Dairy, String> implements IDairyService {
	
	private IDairyDAO dairyDAO;

	@Resource(name = "dairyDAO")
	@Override
	public void setBaseDAO(IBaseDAO<Dairy, String> baseDAO) {
		this.baseDAO = baseDAO;
	}

	public IDairyDAO getDairyDAO() {
		return dairyDAO;
	}

	@Resource
	public void setDairyDAO(IDairyDAO dairyDAO) {
		this.dairyDAO = dairyDAO;
	}

	@Override
	public void auditPass(Dairy dairy) throws Exception {
		JdbcTemplate jt = new JdbcTemplate();
		Context context = null;
		context = ContextBindings.getClassLoader();
		context = (Context) context.lookup("comp/env");
		DataSource dataSource = (DataSource) context.lookup("jdbc/pkm");
		jt.setDataSource(dataSource);
		jt.update("update sh_dairy set sh_dairy.`check` = ? where id = ?", new Object[]{1,dairy.getId()});
	}

	@Override
	public void auditNotPass(Dairy dairy) throws Exception {
		JdbcTemplate jt = new JdbcTemplate();
		Context context = null;
		context = ContextBindings.getClassLoader();
		context = (Context) context.lookup("comp/env");
		DataSource dataSource = (DataSource) context.lookup("jdbc/pkm");
		jt.setDataSource(dataSource);
		jt.update("update sh_dairy set sh_dairy.`check` = ? where id = ?", new Object[]{2,dairy.getId()});
	}

}
