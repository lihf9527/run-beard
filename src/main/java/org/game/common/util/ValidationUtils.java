package org.game.common.util;

import org.game.common.exception.BizException;
import lombok.val;

import javax.validation.Validator;

public class ValidationUtils {
    private static final Validator validator = SpringUtils.getBean(Validator.class);

    private ValidationUtils() {
    }

    public static <T> void validate(T form, Class... classes) {
        val result = validator.validate(form, classes);
        val iterator = result.iterator();
        Assert.isFalse(iterator.hasNext(), () -> new BizException(iterator.next().getMessage()));
    }
}
