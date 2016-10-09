package com.dtr.oas.model;

import com.dtr.oas.enums.Roles;

import javax.persistence.*;

@Entity
@Table(name="user_role")
public class UserRole extends BaseEntity{

	private Integer userId;

	private Roles roleName;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "roleName")
	public Roles getRoleName() {
		return roleName;
	}

	public void setRoleName(Roles roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString( ) {
		return "UserRole{" +
				"userId=" + userId +
				", roleName='" + roleName + '\'' +
				'}';
	}
}
