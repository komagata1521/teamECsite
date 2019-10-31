package com.internousdev.arizona.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;

	public String execute() {

/**
 * 		＊＊キャストの方法として「(変換後変数型)　変換元変数」はNULLを許容できないので使わない＊＊
 * 		＊＊同じ理由で.toString()ではなく.valueOf()を使用する＊＊
 *		sessionにあるsavedUserIdFlagの値がNULLとなる原因である、loginActionでfalseの値をputしないこと、
 *		及びLogoutActionではチェックしないセッションタイムアウトとなった場合を考慮する必要があるため
 */

//		ログアウト処理(=セッション情報を削除)する前にユーザーIDとユーザーID保存フラグの値を取得する
		String userId = String.valueOf(session.get("userId"));
		boolean savedUserIdFlag = Boolean.valueOf(String.valueOf(session.get("savedUserIdFlag")));

//		ログアウト処理のため、ログイン認証情報（session情報）を削除する
		session.clear();

//		ログイン認証時にユーザーID保存フラグにチェックが入っていた場合、ユーザーIDと保存フラグの値をセッションに格納
		if(savedUserIdFlag){
			session.put("savedUserIdFlag", savedUserIdFlag);
			session.put("userId", userId);
		}

		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
