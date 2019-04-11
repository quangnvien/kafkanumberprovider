package vn.quang.entity;

import java.io.Serializable;

/**
 * @author Quang N. Vien {@literal <vienngocquang@gmail.com>}
 */
public class TheNumber implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private Integer value;
	
	public TheNumber() {}

	public TheNumber(String id, Integer value) {
		this.id = id;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}