package org.gordianknot.conf.web.common;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
/**
* 獲取登錄用戶信息工具類
* @author aaron
*
*/
public class LoginUserInfoUtils {
    
    /**
     * 獲取登錄用戶名
     * @param request
     * @return
     */
    public static String getLoginUsername(HttpServletRequest request) {
        if (request.getSession().getAttribute("login_user_name") == null) {
            return null;
        }
        return request.getSession().getAttribute("login_user_name").toString();
    }
    
    /**
     * 獲取登錄用戶能操作的環境
     * @author aaron
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<String> getLoginUserEvns(HttpServletRequest request) {
        if (request.getSession().getAttribute("login_user_envs") == null) {
            return null;
        }
        return (List<String>)request.getSession().getAttribute("login_user_envs");
    }
}
