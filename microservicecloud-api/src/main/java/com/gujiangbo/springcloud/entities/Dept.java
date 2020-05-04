package com.gujiangbo.springcloud.entities;

import java.io.Serializable;

public class Dept implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3133651895919469237L;

	private Long deptno;

	private String dname;

	private String db_source;

	public Dept() {
	}

	public Long getDeptno() {
		return deptno;
	}

	public void setDeptno(Long deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getDb_source() {
		return db_source;
	}

	public void setDb_source(String db_source) {
		this.db_source = db_source;
	}

	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", dname=" + dname + ", db_source=" + db_source + "]";
	}

}
