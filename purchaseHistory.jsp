<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/purchaseHistory.css">
<link rel="stylesheet" type="text/css" href="./css/home.css">
<title>購入履歴一覧画面</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="contents">
		<div id="title">
			<h1>商品購入履歴機能</h1>
		</div>
		<!-- 	Listが空でないときに購入履歴を表示する -->
		<s:if test="!purchaseHistoryInfoDTOList.isEmpty()">
			<table>
				<tr>
					<th class="itemName">商品名</th>
					<th class="itemNameKana">ふりがな</th>
					<th class="itemImage">商品画像</th>
					<th class="releaseCompany">発売会社名</th>
					<th class="releaseDate">発売年月日</th>
					<th class="price">値段</th>
					<th class="productCount">個数</th>
					<th class="totalFee">合計金額</th>
					<th class="name">宛先名前</th>
					<th class="address">宛先住所</th>
				</tr>
				<!-- 	 ループ処理で購入履歴を取得し表示する-->
				<s:iterator value="purchaseHistoryInfoDTOList">
					<tr>
						<td><s:property value="productName" /></td>
						<td><s:property value="productNameKana" /></td>
						<td><img
							src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>'
							width="50px" height="50px" /></td>
						<td><s:property value="releaseCompany" /></td>
						<td><s:property value="releaseDate" /></td>
						<td><s:property value="price" />円</td>
						<td><s:property value="productCount" />個</td>
						<td><s:property value="totalFee" />円</td>
						<td><s:property value="familyName" /><span> </span>
						<s:property value="firstName" /></td>
						<td><s:property value="userAddress" /></td>
					</tr>
				</s:iterator>
			</table>
			<div id=del_btn>
				<s:form action="DeletePurchaseHistoryAction">
					<s:submit value="履歴削除" class="submit_btn" />
				</s:form>
			</div>
		</s:if>
		<!-- 	Listが空のときメッセージを表示する -->
		<s:else>
			<div id="message">
				商品購入履歴情報がありません。
			</div>
		</s:else>
	</div>
</body>
</html>