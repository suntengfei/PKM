package cn.edu.cust.common.service;

import java.util.List;

import cn.edu.cust.common.util.Page;

public interface IBaseService<M extends java.io.Serializable, PK extends java.io.Serializable> {
    
    public void add(M obj) throws Exception;
    
    public void update(M obj) throws Exception;

    public void del(PK id) throws Exception;

    public void delObj(M obj) throws Exception;

    public M load(PK id) throws Exception;
    
    public List<M> list() throws Exception;
    
    public List<M> list(String hql, List<Object> params, Page page) throws Exception;
	
	public List<M> list(String hql, List<Object> params) throws Exception;

	public List<M> list(String hql, Object[] params) throws Exception;
    
}
