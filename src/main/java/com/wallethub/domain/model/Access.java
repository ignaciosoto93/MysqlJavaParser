package com.wallethub.domain.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "logsTable",
	   indexes = { @Index(columnList = "date", name = "dateTimeIndex"), @Index(columnList = "ip", name = "ipIndex") })

public class Access extends BaseEntity {
	@Column(columnDefinition = "INT(4) UNSIGNED")
	private Long ip;
	@Column(columnDefinition = "Timestamp(3)")
	private LocalDateTime date;

	@Column
	private String requestInfo;

	@Column
	private String responseStatus;

	@Column(length = 250)
	private String extraInfo;

	public Long getIp() {
		return ip;
	}

	public void setIp(Long ip) {
		this.ip = ip;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getRequestInfo() {
		return requestInfo;
	}

	public void setRequestInfo(String requestInfo) {
		this.requestInfo = requestInfo;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public static final class AccessBuilder {
		protected Long id;
		private Long ip;
		private LocalDateTime date;
		private String requestInfo;
		private String responseStatus;
		private String extraInfo;

		private AccessBuilder() {
		}

		public AccessBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public AccessBuilder withIp(Long ip) {
			this.ip = ip;
			return this;
		}

		public AccessBuilder withDate(LocalDateTime date) {
			this.date = date;
			return this;
		}

		public AccessBuilder withRequestInfo(String requestInfo) {
			this.requestInfo = requestInfo;
			return this;
		}

		public AccessBuilder withResponseStatus(String responseStatus) {
			this.responseStatus = responseStatus;
			return this;
		}

		public AccessBuilder withExtraInfo(String extraInfo) {
			this.extraInfo = extraInfo;
			return this;
		}

		public Access build() {
			Access access = new Access();
			access.setId(id);
			access.setIp(ip);
			access.setDate(date);
			access.setRequestInfo(requestInfo);
			access.setResponseStatus(responseStatus);
			access.setExtraInfo(extraInfo);
			return access;
		}
	}

	public static AccessBuilder builder() {
		return new AccessBuilder();
	}

}
