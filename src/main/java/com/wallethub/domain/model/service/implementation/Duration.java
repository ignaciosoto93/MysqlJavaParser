package com.wallethub.domain.model.service.implementation;

public enum Duration {
	daily {
		public long getDurationInHours() {
			return 24;
		}
	}, hourly {
		public long getDurationInHours() {
			return 1;
		}
	};

	public abstract long getDurationInHours();

}
