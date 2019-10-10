package com.aaron.spring_cloud_gateway.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 自定義異常處理
 * 
 * <p>異常時用JSON代替HTML異常信息<p>
 * 
 * @author aaron
 *
 */
public class JsonExceptionHandler extends DefaultErrorWebExceptionHandler {

	public JsonExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
			ErrorProperties errorProperties, ApplicationContext applicationContext) {
		super(errorAttributes, resourceProperties, errorProperties, applicationContext);
	}

	/**
	 * 獲取異常屬性
	 */
	@Override
	protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
		int code = 500;
		Throwable error = super.getError(request);
		if (error instanceof org.springframework.cloud.gateway.support.NotFoundException) {
			code = 404;
		}
		return response(code, this.buildMessage(request, error));
	}

	/**
	 * 指定響應處理方法為JSON處理的方法
	 * @param errorAttributes
	 */
	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
	}

	/**
	 * 根據code獲取對應的HttpStatus
	 * @param errorAttributes
	 */
	@Override
	protected HttpStatus getHttpStatus(Map<String, Object> errorAttributes) {
		int statusCode = (int) errorAttributes.get("code");
		return HttpStatus.valueOf(statusCode);
	}

	/**
	 * 構建異常信息
	 * @param request
	 * @param ex
	 * @return
	 */
	private String buildMessage(ServerRequest request, Throwable ex) {
		StringBuilder message = new StringBuilder("Failed to handle request [");
		message.append(request.methodName());
		message.append(" ");
		message.append(request.uri());
		message.append("]");
		if (ex != null) {
			message.append(": ");
			message.append(ex.getMessage());
		}
		return message.toString();
	}

	/**
	 * 構建返回的JSON數據格式
	 * @param status		狀態碼
	 * @param errorMessage  異常信息
	 * @return
	 */
	public static Map<String, Object> response(int status, String errorMessage) {
		Map<String, Object> map = new HashMap<>();
		map.put("code", status);
		map.put("message", errorMessage);
		map.put("data", null);
		return map;
	}
}

