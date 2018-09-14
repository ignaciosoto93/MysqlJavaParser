package com.ef.Parser.domain.model;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.ef.Parser.domain.BaseEntity;

@Entity
@Table(name = "blockedIP")
public class BlockedIP extends BaseEntity {
	@Column(columnDefinition = "BIGINT UNSIGNED")
	private BigInteger ip;
	@Column
	private String reason;

	public BigInteger getIp() {
		return ip;
	}

	public String getReason() {
		return reason;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		protected Long id;
		private BigInteger ip;
		private String reason;

		private Builder() {
		}

		public static Builder aBlockedIP() {
			return new Builder();
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withIp(BigInteger ip) {
			this.ip = ip;
			return this;
		}

		public Builder withReason(String reason) {
			this.reason = reason;
			return this;
		}

		public BlockedIP build() {
			BlockedIP blockedIP = new BlockedIP();
			blockedIP.setId(id);
			blockedIP.ip = this.ip;
			blockedIP.reason = this.reason;
			return blockedIP;
		}
	}
}
