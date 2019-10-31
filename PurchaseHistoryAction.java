package com.internousdev.arizona.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.arizona.dao.PurchaseHistoryInfoDAO;
import com.internousdev.arizona.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class PurchaseHistoryAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList;
	private PurchaseHistoryInfoDAO dao = new PurchaseHistoryInfoDAO();

	public String execute() {

/**
 * 		セッション情報に仮ユーザーIDとユーザーIDの両方が無い場合、及びログインフラグの値が１以外のとき、
 * 		セッションタイムアウトとして、セッションタイムアウト画面に遷移する
 */
		if (!session.containsKey("tempUserId") && !session.containsKey("userId")) {
			return "sessionTimeout";
		}
		int logined = Integer.parseInt(session.get("logined").toString());
		if (logined != 1) {
			return "sessionTimeout";
		}

//		購入履歴情報
		try {
			purchaseHistoryInfoDTOList = dao.getPurchaseHistoryInfo(session.get("userId").toString());
		} catch (SQLException e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryInfoDTOList() {
		return purchaseHistoryInfoDTOList;
	}

	public void setPurchaseHistoryInfoDTOList(List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList) {
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
