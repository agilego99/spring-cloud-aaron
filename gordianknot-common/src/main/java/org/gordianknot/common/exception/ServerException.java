package org.gordianknot.common.exception;

import org.gordianknot.common.base.ResponseCode;

public class ServerException extends GlobalException {
	private static final long serialVersionUID = -1355046108056594333L;
	
	public ServerException(String message) {
        super(message, ResponseCode.SERVER_ERROR_CODE.getCode());
    }

}
