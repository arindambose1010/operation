package com.ahct.login.vo;

import java.util.List;

public class MenuVo implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String MODNAME;
    private String MODID;
    private String MODURL;
    private String PARNTMODID;
    private String MODORDER;
    private Number MODORDER1;
    private String MODDESC;
    private List<MenuVo> listSubMenu=null;
	
    
	public String getMODNAME() {
		return MODNAME;
	}
	public void setMODNAME(String mODNAME) {
		MODNAME = mODNAME;
	}
	public String getMODID() {
		return MODID;
	}
	public void setMODID(String mODID) {
		MODID = mODID;
	}
	public String getMODURL() {
		return MODURL;
	}
	public void setMODURL(String mODURL) {
		MODURL = mODURL;
	}
	public String getPARNTMODID() {
		return PARNTMODID;
	}
	public void setPARNTMODID(String pARNTMODID) {
		PARNTMODID = pARNTMODID;
	}
	public String getMODORDER() {
		return MODORDER;
	}
	public void setMODORDER(String mODORDER) {
		MODORDER = mODORDER;
	}
	public String getMODDESC() {
		return MODDESC;
	}
	public void setMODDESC(String mODDESC) {
		MODDESC = mODDESC;
	}
	public List<MenuVo> getListSubMenu() {
		return listSubMenu;
	}
	public void setListSubMenu(List<MenuVo> listSubMenu) {
		this.listSubMenu = listSubMenu;
	}
	public Number getMODORDER1() {
		return MODORDER1;
	}
	public void setMODORDER1(Number mODORDER1) {
		MODORDER1 = mODORDER1;
	}

}
