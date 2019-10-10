package org.gordianknot.common.util;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import org.gordianknot.common.base.ResponseCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
/**
 * API 調用認證工具類，採用 RSA 加密
 * @author aaron
 *
 */
public class JWTUtils {
 	private static RSAPrivateKey priKey;
 	private static RSAPublicKey pubKey;
 	private static class SingletonHolder {
 		private static final JWTUtils INSTANCE = new JWTUtils();
 	}
 	public synchronized static JWTUtils getInstance(String modulus, String privateExponent, String publicExponent) {
 		if (priKey == null && pubKey == null) {
 			priKey = RSAUtils.getPrivateKey(modulus, privateExponent);
 			pubKey = RSAUtils.getPublicKey(modulus, publicExponent);
 		}
 		return SingletonHolder.INSTANCE;
 	}
 	public synchronized static void reload(String modulus, String privateExponent, String publicExponent) {
 		priKey = RSAUtils.getPrivateKey(modulus, privateExponent);
 		pubKey = RSAUtils.getPublicKey(modulus, publicExponent);
 	}
 	
 	public synchronized static JWTUtils getInstance() {
 		if (priKey == null && pubKey == null) {
 			priKey = RSAUtils.getPrivateKey(RSAUtils.modulus, RSAUtils.private_exponent);
 			pubKey = RSAUtils.getPublicKey(RSAUtils.modulus, RSAUtils.public_exponent);
 		}
 		return SingletonHolder.INSTANCE;
 	}
 	
 	/**
 	* 獲取Token
 	* @param uid 用戶ID
 	* @param exp 失效時間，單位分鐘
 	* @return
 	*/
 	public static String getToken(String uid, int exp) {
 		long endTime = System.currentTimeMillis() + 1000 * 60 * exp;
 		return Jwts.builder().setSubject(uid).setExpiration(new Date(endTime))
 				.signWith(SignatureAlgorithm.RS512, priKey).compact();
 	}
 	/**
 	* 獲取Token
 	* @param uid 用戶ID
 	* @return
 	*/
 	public String getToken(String uid) {
 		long endTime = System.currentTimeMillis() + 1000 * 60 * 1440;
 		return Jwts.builder().setSubject(uid).setExpiration(new Date(endTime))
 				.signWith(SignatureAlgorithm.RS512, priKey).compact();
 	}
 	/**
 	* 檢查Token是否合法
 	* @param token
 	* @return JWTResult
 	*/
 	public JWTResult checkToken(String token) {
 		try {
 			Claims claims = Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token).getBody();
 			String sub = claims.get("sub", String.class);
 			return new JWTResult(true, sub, "合法請求", ResponseCode.SUCCESS_CODE.getCode());
 		} catch (ExpiredJwtException e) {
 			// 在解析JWT字符串時，如果‘過期時間字段’已經早於當前時間，將會拋出ExpiredJwtException異常，說明本次請求已經失效
 			return new JWTResult(false, null, "token已過期", ResponseCode.TOKEN_TIMEOUT_CODE.getCode());
 		} catch (SignatureException e) {
 			// 在解析JWT字符串時，如果密鑰不正確，將會解析失敗，拋出SignatureException異常，說明該JWT字符串是偽造的
 			return new JWTResult(false, null, "非法請求", ResponseCode.NO_AUTH_CODE.getCode());
 		} catch (Exception e) {
 			return new JWTResult(false, null, "非法請求", ResponseCode.NO_AUTH_CODE.getCode());
 		}
 	}
 	public static class JWTResult {
 		private boolean status;
 		private String uid;
 		private String msg;
 		private int code;
 		
 		public JWTResult() {
 			super();
 		}
 		public JWTResult(boolean status, String uid, String msg, int code) {
 			super();
 			this.status = status;
 			this.uid = uid;
 			this.msg = msg;
 			this.code = code;
 		}
 		
 		public int getCode() {
 			return code;
 		}
 		public void setCode(int code) {
 			this.code = code;
 		}
 		public String getMsg() {
 			return msg;
 		}
 		public void setMsg(String msg) {
 			this.msg = msg;
 		}
 		public boolean isStatus() {
 			return status;
 		}
 		public void setStatus(boolean status) {
 			this.status = status;
 		}
 		public String getUid() {
 			return uid;
 		}
 		public void setUid(String uid) {
 			this.uid = uid;
 		}
 	}
}
