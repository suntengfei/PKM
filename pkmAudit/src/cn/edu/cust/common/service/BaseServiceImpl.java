package cn.edu.cust.common.service;

import java.util.List;

import cn.edu.cust.common.dao.IBaseDAO;
import cn.edu.cust.common.util.Page;

public abstract class BaseServiceImpl<M extends java.io.Serializable, PK extends java.io.Serializable> implements IBaseService<M, PK> {
    
    protected IBaseDAO<M, PK> baseDAO;
    
    public abstract void setBaseDAO(IBaseDAO<M, PK> baseDAO);

    @Override
    public void add(M obj) throws Exception {
        baseDAO.add(obj);
    }

    @Override
    public void update(M obj) throws Exception {
        baseDAO.update(obj);
    }
    
    @Override
    public void del(PK id) throws Exception {
        baseDAO.del(id);
    }

    @Override
    public void delObj(M obj) throws Exception {
        baseDAO.delObj(obj);
    }

    @Override
    public M load(PK id) throws Exception {
        return baseDAO.load(id);
    }
    
    @Override
    public List<M> list() throws Exception {
        return baseDAO.list();
    }

	@Override
	public List<M> list(String hql, List<Object> params, Page page) throws Exception {
		 return baseDAO.list(hql, params, page);
	}

	@Override
	public List<M> list(String hql, List<Object> params) throws Exception {
		 return baseDAO.list(hql, params);
	}

	@Override
	public List<M> list(String hql, Object[] params) throws Exception {
		 return baseDAO.list(hql, params);
	}
    
}
