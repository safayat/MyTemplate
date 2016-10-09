package com.dtr.oas.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue
	private Integer id;

	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date creationDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public static List<Integer> fetchIdList(List<BaseEntity> list){
		List<Integer> idList = new ArrayList<>();
		for (BaseEntity entity : list){
			idList.add(entity.getId());
		}
		return idList;
	}
	public static <E extends BaseEntity> Map<Integer, E> toMap(List<E> list){
		Map<Integer, E> map = new HashMap();
		for (E entity : list){
			map.put(entity.getId(),entity);
		}
		return map;
	}
}
