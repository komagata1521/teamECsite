package com.internousdev.arizona.dto;

public class PurchaseHistoryInfoDTO {

	private String productName;// 商品名
	private String productNameKana;// 商品名かな
	private String imageFilePath;// 画像ファイルパス
	private String imageFileName;// 画像ファイル名
	private String releaseCompany;// 発売会社
	private String releaseDate;// 発売日
	private int price;// 値段
	private int productCount;// 個数
	private int totalFee;// 合計金額
	private String familyName;// 姓
	private String firstName;// 名
	private String userAddress;// 住所

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNameKana() {
		return productNameKana;
	}

	public void setProductNameKana(String poductNameKana) {
		this.productNameKana = poductNameKana;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getReleaseCompany() {
		return releaseCompany;
	}

	public void setReleaseCompany(String releaseCompany) {
		this.releaseCompany = releaseCompany;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(int totlalFee) {
		this.totalFee = totlalFee;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

}
