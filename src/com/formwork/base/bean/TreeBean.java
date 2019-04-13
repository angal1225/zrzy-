package com.formwork.base.bean;

public class TreeBean {
	private String id;
	private String pId;
	private String name;
	private String open;
	private String isParent="0";
	private String url;
	private String title;
	private String link;
	private int lvl; 
	private String highlight;
	private String checked;
	private String icon;
	
	public TreeBean(){
	}
	
	public TreeBean(String id, String name, String pId, String isParent, String link, String open, String title, String url){
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
		this.isParent = isParent;
		this.url = url;
		this.title = title;
		this.link = link;
	}
	
	public TreeBean(String id, String name, String pId, String isParent){
		this(id, name, pId, isParent, "", "", "", "");
	}

	public TreeBean(String id, String name, String pId, String isParent, String link){
		this(id, name, pId, isParent, link, "", "", "");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
