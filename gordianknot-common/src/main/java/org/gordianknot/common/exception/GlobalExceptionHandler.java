package org.gordianknot.common.exception;
import javax.servlet.http.HttpServletRequest;
import org.gordianknot.common.base.ResponseCode;
import org.gordianknot.common.base.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 全局異常處理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
 	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
 	
 	/**
 	* 自定義異常處理
 	* @param req
 	* @param e
 	* @return
 	* @throws Exception
 	*/
 	@ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public ResponseData jsonErrorHandler(HttpServletRequest req, GlobalException e) throws Exception {
 		logger.error("", e);
 		ResponseData r = new ResponseData();
        r.setMessage(e.getMessage());
        r.setCode(e.getCode());
        r.setData(null);
        return r;
    }
 	
 	/**
 	* 系統異常處理，比如：404,500
 	* @param req
 	* @param e
 	* @return
 	* @throws Exception
 	*/
 	@ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseData defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
 		logger.error("", e);
 		ResponseData r = new ResponseData();
        r.setMessage(e.getMessage());
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
        	r.setCode(ResponseCode.NOT_FOUND.getCode());
 		} else {
 			r.setCode(ResponseCode.SERVER_ERROR_CODE.getCode());
 		}
        r.setData(null);
        return r;
    }
}
