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
			<!--  テーブルセル幅の比率は「宛先名4文字,宛先住所20文字」の場合を基準に一部を微調整して定義した -->
				<colgroup width="6.3%">
				<colgroup width="7.7%">
				<colgroup width="7.7%">
				<colgroup width="9.9%">
				<colgroup width="9.1%">
				<colgroup width="6.2%">
				<colgroup width="4.9%">
				<colgroup width="7.7%">
				<colgroup width="8.3%">
				<colgroup width="32.2%">
				<tr>
					<th>商品名</th>
					<th>ふりがな</th>
					<th>商品画像</th>
					<th>発売会社名</th>
					<th>発売年月日</th>
					<th>値段</th>
					<th>個数</th>
					<th>合計金額</th>
					<th>宛先名前</th>
					<th>宛先住所</th>
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