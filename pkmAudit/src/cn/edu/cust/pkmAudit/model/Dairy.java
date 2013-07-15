package cn.edu.cust.pkmAudit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "sh_dairy")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dairy implements Serializable {
	
	private static final long serialVersionUID = -8399424948346577312L;
	
	/**
	 * 编号
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "time")
	private String time;
	
	@Column(name = "etime")
	private Long etime;
	
	/**
	 * 处理状态
	 * 0 未审核
	 * 1 审核通过
	 * 2 审核不通过
	 */
	@Column(name = "check")
	private int check;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Long getEtime() {
		return etime;
	}

	public void setEtime(Long etime) {
		this.etime = etime;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

}
