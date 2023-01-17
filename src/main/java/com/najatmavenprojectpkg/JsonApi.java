package com.najatmavenprojectpkg;

public class JsonApi {
	
	//properties from json.
	
	    private int api_id;
		private String [] web_pages;
		private String state_province;
		private String alpha_two_code;
		private String name;
		private String country;
		private String [] domains; //[] list of. shows from json.
		
		public int getApi_id() {
			return api_id;
		}
		public void setApi_id(int api_id) {
			this.api_id = api_id;
		}
		public String[] getWeb_pages() {
			return web_pages;
		}
		public void setWeb_pages(String[] web_pages) {
			this.web_pages = web_pages;
		}
		public String getState_province() {
			return state_province;
		}
		public void setState_province(String state_province) {
			this.state_province = state_province;
		}
		public String getAlpha_two_code() {
			return alpha_two_code;
		}
		public void setAlpha_two_code(String alpha_two_code) {
			this.alpha_two_code = alpha_two_code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String[] getDomains() {
			return domains;
		}
		public void setDomains(String[] domains) {
			this.domains = domains;
		}
		
		

	

}
