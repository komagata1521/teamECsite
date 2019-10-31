package com.internousdev.arizona.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.arizona.dao.PurchaseHistoryInfoDAO;
import com.internousdev.arizona.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeletePurchaseHistoryAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList;
	private PurchaseHistoryInfoDAO dao = new PurchaseHistoryInfoDAO();

	public String execute() {

//		想定外での履歴情報表示を防ぐため、初期値をERRORとする
		String result = ERROR;

/**
 * 		セッション情報に仮ユーザーIDとユーザーIDの両方がない場合、及びログインフラグの値が1以外のとき、
 * 		セッションタイムアウトとしてセッションタイムアウト画面に遷移する
 */
		if (!session.containsKey("tempUserId") && !session.containsKey("userId")) {
			result = "sessionTimeout";
		}

		int logined = Integer.parseInt(session.get("logined").toString());
		if (logined != 1) {
			result = "sessionTimeout";
		}

//		履歴の削除処理
		int count;
		try {
			count = dao.deletePurchaseHistoryInfo(session.get("userId").toString());
		} catch (SQLException e) {
			e.printStackTrace();
			return ERROR;
		}

//		削除件数が1以上のとき正常に処理が実行されたとしてresultの値をSUCCESSに書き換える
		if (count > 0) {
			try {
				purchaseHistoryInfoDTOList = dao.getPurchaseHistoryInfo(session.get("userId").toString());
			} catch (SQLException e) {
				e.printStackTrace();
				return ERROR;
			}
			result = SUCCESS;
		} else {
			result = ERROR;
		}

		return result;
	}

	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryInfoDTOList() {
		return purchaseHistoryInfoDTOList;
	}

	public void setPurchaseHistoryInfoList(List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList) {
		this.purchaseHistoryInfoDTOList = purchaseHistoryInfoDTOList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
