package com.internousdev.arizona.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.arizona.dto.PurchaseHistoryInfoDTO;
import com.internousdev.arizona.util.DBConnector;

public class PurchaseHistoryInfoDAO {

	/**
	 * 決済処理を完了し、購入履歴情報をデータベースに登録する
	 * @param userId：ユーザーID
	 * @param productId：商品ID
	 * @param productCount：個数
	 * @param price：価格
	 * @param destinationId：宛先ID
	 * @return 登録（挿入）件数
	 * @throws SQLException
	 */

	public int registPurchaseHistoryInfo(String userId, int productId, int productCount, int price, int destinationId) throws SQLException {
		DBConnector db = new DBConnector();
		Connection conn = db.getConnection();

		int count = 0;

		String sql = "INSERT INTO purchase_history_info ("
				+ "user_id, "//ユーザーID
				+ "product_id, "//商品ID
				+ "product_count, "//個数
				+ "price, "//値段
				+ "destination_id, "//宛先情報ID
				+ "regist_date, "//登録日時
				+ "update_date "//更新日時
				+ ") VALUES ("
				+ "?,?,?,?,?,now(),now())";
		/**
		 * 登録日時・更新日時は現在日時を返すMySQLのnow()関数を利用する
		 */

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, productId);
			ps.setInt(3, productCount);
			ps.setInt(4, price);
			ps.setInt(5, destinationId);

			count = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return count;
	}

	/**
	 * ユーザーIDを条件に購入履歴情報を取得する
	 * @param userId：ユーザーID
	 * @return 購入履歴情報のリスト
	 * @throws SQLException
	 */

	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryInfo(String userId) throws SQLException {
		DBConnector db = new DBConnector();
		Connection conn = db.getConnection();
		List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList = new ArrayList<PurchaseHistoryInfoDTO>();

		String sql = "SELECT "
				+ "pi.product_name, "//商品名
				+ "pi.product_name_kana, "//商品名かな
				+ "pi.image_file_path, "//画像ファイルパス
				+ "pi.image_file_name, "//画像ファイル名
				+ "pi.release_company, "//発売会社
				+ "DATE_FORMAT(pi.release_date, '%y/%m/%d') AS release_date, "//発売年月日
				+ "phi.price, "//値段
				+ "phi.product_count, "//個数
				+ "phi.price * phi.product_count AS total_fee, "//合計金額
				+ "di.family_name, "//姓
				+ "di.first_name, "//名
				+ "di.user_address "//住所
				+ "FROM purchase_history_info AS phi "
				+ "LEFT OUTER JOIN product_info AS pi "
				+ "ON phi.product_id = pi.product_id "
				+ "LEFT OUTER JOIN destination_info AS di "
				+ "ON phi.destination_id = di.id "
				+ "WHERE phi.user_id = ? "
				+ "ORDER BY phi.regist_date DESC";

		/**
		 * 登録日時の降順（新しいものから）に情報を取得する
		 */

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				PurchaseHistoryInfoDTO dto = new PurchaseHistoryInfoDTO();
				dto.setProductName(rs.getString("product_name"));// 商品名
				dto.setProductNameKana(rs.getString("product_name_kana"));// 商品名かな
				dto.setImageFilePath(rs.getString("image_file_path"));// 画像ファイルパス
				dto.setImageFileName(rs.getString("image_file_name"));// 画像ファイル名
				dto.setReleaseCompany(rs.getString("release_company"));// 発売会社
				dto.setReleaseDate(rs.getString("release_date"));// 発売年月日
				dto.setPrice(rs.getInt("price"));// 値段
				dto.setProductCount(rs.getInt("product_count"));// 個数
				dto.setTotalFee(rs.getInt("total_fee"));// 合計金額
				dto.setFamilyName(rs.getString("family_name"));// 姓
				dto.setFirstName(rs.getString("first_name"));// 名
				dto.setUserAddress(rs.getString("user_address"));// 宛先住所

				purchaseHistoryInfoDTOList.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return purchaseHistoryInfoDTOList;
	}

	/**
	 * ユーザーIDを条件に購入履歴情報を削除する
	 * @param userId：ユーザーID
	 * @return 削除件数
	 * @throws SQLException
	 */

	public int deletePurchaseHistoryInfo(String userId) throws SQLException {
		DBConnector db  = new DBConnector();
		Connection conn = db.getConnection();

		int count = 0;

		String sql = "DELETE FROM purchase_history_info WHERE user_id = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);

			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return count;
	}
}
