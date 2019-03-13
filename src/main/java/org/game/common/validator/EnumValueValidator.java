package org.game.common.validator;

import org.game.common.EnumMsg;
import org.game.common.annotation.EnumValue;
import org.game.common.util.ClassUtils;
import org.game.common.util.EnumUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {
    private Class<? extends EnumMsg<?>> enumClass;
    private boolean nullable;
    private boolean multiple;
    private String delimiter;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        enumClass = constraintAnnotation.enumClass();
        nullable = constraintAnnotation.nullable();
        multiple = constraintAnnotation.multiple();
        delimiter = constraintAnnotation.delimiter();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (nullable) {
            if (value == null)
                return true;

            if (value instanceof String && "".equals(value)) {
                return true;
            }
        } else {
            if (value == null)
                return false;

            if (value instanceof String && "".equals(value))
                return false;
        }

        if (value instanceof String && multiple) {
            List<String> values = Arrays.asList(String.valueOf(value).split(delimiter));
            Class<?> argsClass = ClassUtils.getGenericOfInterface(enumClass, 0, 0);
            if (argsClass.isAssignableFrom(String.class))
                return EnumUtils.isValid(values, enumClass);

            Method method = ClassUtils.getMethod(argsClass, "valueOf", String.class);
            List<Object> list = values.stream().map(s -> ClassUtils.invoke(method, null, s)).collect(Collectors.toList());
            return EnumUtils.isValid(list, enumClass);
        }

        return EnumUtils.isValid(value, enumClass);
    }
}
