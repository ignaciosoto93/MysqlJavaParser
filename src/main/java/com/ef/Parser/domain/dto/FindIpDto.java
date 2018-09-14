package com.ef.Parser.domain.dto;

import java.time.LocalDateTime;
import com.ef.Parser.domain.service.implementation.Duration;

public class FindIpDto {

	private LocalDateTime startDate;
	private Long threshold;
	private Duration duration;

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public Long getThreshold() {
		return threshold;
	}

	public Duration getDuration() {
		return duration;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private LocalDateTime startDate;
		private Long threshold;
		private Duration duration;

		private Builder() {
		}

		public static Builder FindIpDto() {
			return new Builder();
		}

		public Builder withStartDate(LocalDateTime startDate) {
			this.startDate = startDate;
			return this;
		}

		public Builder withThreshold(Long threshold) {
			this.threshold = threshold;
			return this;
		}

		public Builder withDuration(Duration duration) {
			this.duration = duration;
			return this;
		}

		public FindIpDto build() {
			FindIpDto findIpDto = new FindIpDto();
			findIpDto.startDate = this.startDate;
			findIpDto.threshold = this.threshold;
			findIpDto.duration = this.duration;
			return findIpDto;
		}
	}
}
