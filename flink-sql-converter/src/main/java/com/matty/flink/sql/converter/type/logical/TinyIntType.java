package com.matty.flink.sql.converter.type.logical;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Description:
 *
 * @author mwt
 * @version 1.0
 * @date 2019-10-09
 */
public final class TinyIntType extends LogicalType {

    public static final int PRECISION = 3;

    private static final String FORMAT = "TINYINT";

    private static final Set<String> NULL_OUTPUT_CONVERSION = conversionSet(
            Byte.class.getName());

    private static final Set<String> NOT_NULL_INPUT_OUTPUT_CONVERSION = conversionSet(
            Byte.class.getName(),
            byte.class.getName());

    private static final Class<?> DEFAULT_CONVERSION = Byte.class;

    public TinyIntType(boolean isNullable) {
        super(isNullable, LogicalTypeRoot.TINYINT);
    }

    public TinyIntType() {
        this(true);
    }

    @Override
    public LogicalType copy(boolean isNullable) {
        return new TinyIntType(isNullable);
    }

    @Override
    public String asSerializableString() {
        return withNullability(FORMAT);
    }

    @Override
    public boolean supportsInputConversion(Class<?> clazz) {
        return NOT_NULL_INPUT_OUTPUT_CONVERSION.contains(clazz.getName());
    }

    @Override
    public boolean supportsOutputConversion(Class<?> clazz) {
        if (isNullable()) {
            return NULL_OUTPUT_CONVERSION.contains(clazz.getName());
        }
        return NOT_NULL_INPUT_OUTPUT_CONVERSION.contains(clazz.getName());
    }

    @Override
    public Class<?> getDefaultConversion() {
        return DEFAULT_CONVERSION;
    }

    @Override
    public List<LogicalType> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public <R> R accept(LogicalTypeVisitor<R> visitor) {
        return visitor.visit(this);
    }
}