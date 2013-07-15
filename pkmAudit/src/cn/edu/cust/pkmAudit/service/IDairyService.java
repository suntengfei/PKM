package cn.edu.cust.pkmAudit.service;

import cn.edu.cust.common.service.IBaseService;
import cn.edu.cust.pkmAudit.model.Dairy;

public interface IDairyService extends IBaseService<Dairy, String> {

	public void auditPass(Dairy dairy) throws Exception;

	public void auditNotPass(Dairy dairy) throws Exception;
	
}
