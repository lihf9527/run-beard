package org.game.common.exception;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException {
    private int code;

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, int code) {
        this(message);
        this.code = code;
    }
}
