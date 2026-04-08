package com.ahct.model;


import javax.persistence.Column;


public class EhfmHospHubSpokeMpgId implements java.io.Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String hubHospId;
		private String spokeHospId;
		private String hubUserId;
		private String spokeUserId;
		
		@Column(name="HUB_HOSP_ID",nullable=false)
		public String getHubHospId() {
			return hubHospId;
		}
		public void setHubHospId(String hubHospId) {
			this.hubHospId = hubHospId;
		}
		@Column(name="SPOKE_HOSP_ID",nullable=false)
		public String getSpokeHospId() {
			return spokeHospId;
		}
		public void setSpokeHospId(String spokeHospId) {
			this.spokeHospId = spokeHospId;
		}
		@Column(name="HUB_USER_ID",nullable=false)
		public String getHubUserId() {
			return hubUserId;
		}
		public void setHubUserId(String hubUserId) {
			this.hubUserId = hubUserId;
		}
		@Column(name="SPOKE_USER_ID",nullable=false)
		public String getSpokeUserId() {
			return spokeUserId;
		}
		public void setSpokeUserId(String spokeUserId) {
			this.spokeUserId = spokeUserId;
		}
		public EhfmHospHubSpokeMpgId(String hubHospId, String spokeHospId,String hubUserId,String spokeUserId) {
			super();
			this.hubHospId = hubHospId;
			this.spokeHospId = spokeHospId;
			this.hubUserId = hubUserId;
			this.spokeUserId = spokeUserId;
		}
		public EhfmHospHubSpokeMpgId() {
			super();
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((hubHospId == null) ? 0 : hubHospId.hashCode());
			result = prime * result
					+ ((spokeHospId == null) ? 0 : spokeHospId.hashCode());
			result = prime * result
					+ ((hubUserId == null) ? 0 : hubUserId.hashCode());
			result = prime * result
					+ ((spokeUserId == null) ? 0 : spokeUserId.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			EhfmHospHubSpokeMpgId other = (EhfmHospHubSpokeMpgId) obj;
			if (hubHospId == null) {
				if (other.hubHospId != null)
					return false;
			} else if (!hubHospId.equals(other.hubHospId))
				return false;
			if (spokeHospId == null) {
				if (other.spokeHospId != null)
					return false;
			} else if (!spokeHospId.equals(other.spokeHospId))
				return false;
			if (hubUserId == null) {
				if (other.hubUserId != null)
					return false;
			} else if (!hubUserId.equals(other.hubUserId))
				return false;
			if (spokeUserId == null) {
				if (other.spokeUserId != null)
					return false;
			} else if (!spokeUserId.equals(other.spokeUserId))
				return false;
			return true;
		}
}
