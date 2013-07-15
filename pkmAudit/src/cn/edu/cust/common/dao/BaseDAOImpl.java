package cn.edu.cust.common.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cn.edu.cust.common.util.Assert;
import cn.edu.cust.common.util.Page;

public abstract class BaseDAOImpl<M extends java.io.Serializable, PK extends java.io.Serializable> implements IBaseDAO<M, PK> {

    private final Class<M> entityClass;
    private final String HQL_LIST_ALL;
    private String pkName = null;

    @SuppressWarnings("unchecked")
    public BaseDAOImpl() {
        this.entityClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Field[] fields = this.entityClass.getDeclaredFields();
        for(Field f : fields) {
            if(f.isAnnotationPresent(Id.class)) {
                this.pkName = f.getName();
            }
        }
        
        Assert.notNull(pkName);
        
        HQL_LIST_ALL = "from " + this.entityClass.getSimpleName() + " order by " + pkName + " desc";
    }
        
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public Session getSession() {
        //事务必须是开启的(Required)，否则获取不到
        return sessionFactory.getCurrentSession();
    }
    
    /**
     * 添加
     */
    @Override
    public void add(M obj) {
        getSession().save(obj);
    }
    
    /**
     * 修改
     */
    @Override
    public void update(M obj) {
        getSession().update(obj);
    }

    /**
     * 删除 由id
     */
    @Override
    public void del(PK id) {
        getSession().delete(this.load(id));
    }

    /**
     * 删除 由对象
     */
    @Override
    public void delObj(M obj) {
        getSession().delete(obj);
    }

    /**
     * 加载
     */
    @SuppressWarnings("unchecked")
	@Override
    public M load(PK id) {
        return (M) getSession().get(this.entityClass, id);
    }

    /**
     * 列出所有对象
     */
    @Override
    public List<M> list() {
        return list(HQL_LIST_ALL, new Object[]{});
    }
    
    /**
     * 列出所有对象 分页 查询
     */
    @SuppressWarnings("unchecked")
	@Override
	public List<M> list(final String hql, final List<Object> params, final Page page) {
		Query query = getSession().createQuery(hql);
		setParameters(query, params);
		page.setRowCount(query.list().size());
		query.setFirstResult(page.getOffset());
		query.setMaxResults(page.getPageSize());
		return query.list();
	}
    
    /**
     * 列出所有对象 查询 List条件
     * @param hql
     * @param paramlist
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<M> list(final String hql, final List<Object> params) {
    	Query query = getSession().createQuery(hql);
    	setParameters(query, params);
    	return query.list();
    }
    
    /**
     * 列出所有对象 查询 数组条件
     * @param hql
     * @param paramlist
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
	public List<M> list(final String hql, final Object[] params) {
        Query query = getSession().createQuery(hql);
        setParameters(query, params);
        return query.list();
    }
    
    /**
     * 设置参数 数组 到Query
     * @param query
     * @param params
     */
    protected void setParameters(Query query, Object[] params) {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if(params[i] instanceof Date) {
                    query.setTimestamp(i, (Date)params[i]);
                } else {
                    query.setParameter(i, params[i]);
                }
            }
        }
    }
    
    /**
     * 设置参数 List 到Query
     * @param query
     * @param params
     */
    protected void setParameters(Query query, List<Object> params) {
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
            	Object obj = params.get(i);
                if(obj instanceof Date) {
                    query.setTimestamp(i, (Date)obj);
                } else {
                    query.setParameter(i, obj);
                }
            }
        }
    }

}