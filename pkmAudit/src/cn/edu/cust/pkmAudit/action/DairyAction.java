package cn.edu.cust.pkmAudit.action;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.edu.cust.common.util.Page;
import cn.edu.cust.pkmAudit.model.Dairy;
import cn.edu.cust.pkmAudit.search.DairySearch;
import cn.edu.cust.pkmAudit.service.IDairyService;

import com.opensymphony.xwork2.ActionSupport;

@Component("dairyAction")
@Scope("prototype")
public class DairyAction extends ActionSupport {
	
	private static final long serialVersionUID = 7485559027954078693L;
	
	private static final Logger log = LoggerFactory.getLogger(DairyAction.class);
	
	private Page page = new Page();
	
	private DairySearch search = new DairySearch();
	
	private IDairyService dairyService;
	
	private int[] ids;
	
	private Dairy obj;
	
	private List<Dairy> objs;
	
	private String successMsg;
	
	private String errorMsg;
	
	private String oldName;
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public DairySearch getSearch() {
		return search;
	}

	public void setSearch(DairySearch search) {
		this.search = search;
	}

	public IDairyService getdairyService() {
		return dairyService;
	}
	
	@Resource
	public void setdairyService(IDairyService dairyService) {
		this.dairyService = dairyService;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public Dairy getObj() {
		return obj;
	}

	public void setObj(Dairy obj) {
		this.obj = obj;
	}

	public List<Dairy> getObjs() {
		return objs;
	}

	public void setObjs(List<Dairy> objs) {
		this.objs = objs;
	}

	public String getSuccessMsg() {
		return successMsg;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	
	public String load() {
		errorMsg = "信息获取失败";
		successMsg = "信息获取成功";
		
		try {
			obj = dairyService.list("from Dairy d where d.id = ?", new Object[]{ids[0]}).get(0);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errorMsg = e.getMessage();
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String show() {
		return load();
	}
	
	public String select4AuditList() {
		errorMsg = "信息获取失败";
		successMsg = "信息获取成功";
		
		String hql = null;
		List<Object> params = null;
		
		
		log.info(hql);
		
		try {
			
			hql = search.buildSQL();
			params = search.getParams();
			
			objs = dairyService.list(hql, params, page);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errorMsg = e.getMessage();
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String auditPass() {
		successMsg = "处理成功";
		errorMsg = "处理失败 ";
		
		try {
			dairyService.auditPass(obj);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errorMsg = e.getMessage();
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String auditNotPass() {
		successMsg = "处理成功";
		errorMsg = "处理失败 ";
		
		try {
			dairyService.auditNotPass(obj);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errorMsg = e.getMessage();
			return ERROR;
		}
		
		return SUCCESS;
	}
	
}
