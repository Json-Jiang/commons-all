/*
 * Cai.xin Inc.
 * Copyright (c) 2016-2019 All Rights Reserved.
 */

package com.jjson.common.util.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * 金额以及优惠率计算工具
 *
 * @author 幽明
 * @serial 2019-07-17
 */
public class MoneyUtils {

    /**
     * 分转元
     *
     * @param cent 单位为分的金额
     * @return 单位为元的金额
     */
    public static BigDecimal centToYuan(Long cent) {
        if (Objects.nonNull(cent)) {
            return new BigDecimal(cent.toString()).movePointLeft(2);
        }

        return null;
    }

    /**
     * 元转分
     *
     * @param yuan 单位为元的金额
     * @return 单位为元的金额
     */
    public static Long yuanToCent(BigDecimal yuan) {
        if (Objects.nonNull(yuan)) {
            return yuan.movePointRight(2).longValue();
        }

        return null;
    }

    /**
     * 相加
     *
     * @param x 加数
     * @param y 被加数
     * @return x + y
     */
    public static BigDecimal add(BigDecimal x, BigDecimal y) {
        if (Objects.nonNull(x) && Objects.nonNull(y)) {
            return x.add(y);
        }

        return Objects.nonNull(x) ? x : y;
    }

    /**
     * 相减
     *
     * @param x 被减数
     * @param y 减数
     * @return x - y
     */
    public static BigDecimal subtract(BigDecimal x, BigDecimal y) {
        if (Objects.nonNull(x) && Objects.nonNull(y)) {
            return x.subtract(y);
        }

        return null;
    }

    /**
     * 金额计算，适用于分*优惠率
     *
     * @param x 单位为分的金额，乘数
     * @param y 被乘数
     * @return x * y，单位为元的金额
     */
    public static BigDecimal multiply(Long x, BigDecimal y) {
        if (Objects.nonNull(x) && Objects.nonNull(y)) {
            return new BigDecimal(x.toString()).movePointLeft(2).multiply(y).setScale(2, RoundingMode.HALF_UP);
        }

        return null;
    }

    /**
     * 金额计算，适用于分*数量
     *
     * @param x 单位为分的金额，乘数
     * @param y 被乘数
     * @return x * y，单位为元的金额
     */
    public static BigDecimal multiply(Long x, Integer y) {
        if (Objects.nonNull(x) && Objects.nonNull(y)) {
            return new BigDecimal(x.toString()).movePointLeft(2).multiply(new BigDecimal(y.toString()));
        }

        return null;
    }

    /**
     * 金额计算，适用于元*数量
     *
     * @param x 单位为元的金额，乘数
     * @param y 被乘数
     * @return x * y，单位为元的金额
     */
    public static BigDecimal multiply(BigDecimal x, Integer y) {
        if (Objects.nonNull(x) && Objects.nonNull(y)) {
            return x.multiply(new BigDecimal(y.toString())).setScale(2, RoundingMode.HALF_UP);
        }

        return null;
    }

    /**
     * 金额计算，适用于元*优惠率
     *
     * @param x 单位为元的金额，乘数
     * @param y 被乘数
     * @return x * y，单位为元的金额，保留2位小数
     */
    public static BigDecimal multiply(BigDecimal x, BigDecimal y) {
        if (Objects.nonNull(x) && Objects.nonNull(y)) {
            return x.multiply(y).setScale(2, RoundingMode.HALF_UP);
        }

        return null;
    }

    /**
     * 金额计算，适用于优惠率计算
     *
     * @param x 单位为分的金额，除数
     * @param y 单位为分的金额，被除数
     * @param scale 指定精度
     * @return x/y
     */
    public static BigDecimal divide(Long x, Long y, Integer scale) {
        if (Objects.nonNull(x) && Objects.nonNull(y)) {
            return new BigDecimal(x.toString()).divide(new BigDecimal(y.toString()), scale, RoundingMode.HALF_UP);
        }

        return null;
    }

    /**
     * 金额计算，适用于优惠率计算
     *
     * @param x 单位为分的金额，除数
     * @param y 单位为分的金额，被除数
     * @param scale 指定精度
     * @return x/y
     */
    public static BigDecimal divide(Integer x, Integer y, Integer scale) {
        if (Objects.nonNull(x) && Objects.nonNull(y)  && !Objects.equals(y, 0)) {
            return new BigDecimal(x.toString()).divide(new BigDecimal(y.toString()), scale, RoundingMode.HALF_UP);
        }

        return null;
    }

    /**
     * 金额计算，适用于优惠率计算
     *
     * @param x 单位为分的金额，除数
     * @param y 单位为元的金额，被除数
     * @return x/y，单位为元的金额，保留2位小数
     */
    public static BigDecimal divide(Long x, BigDecimal y) {
        if (Objects.nonNull(x) && Objects.nonNull(y)) {
            return new BigDecimal(x.toString()).movePointLeft(2).divide(y, 2, RoundingMode.HALF_UP);
        }

        return null;
    }

    /**
     * 金额计算，适用于优惠率计算
     *
     * @param x 单位为元的金额，除数
     * @param y 单位为元的金额，被除数
     * @return x/y，单位为元的金额，保留2位小数
     */
    public static BigDecimal divide(BigDecimal x, BigDecimal y) {
        if (Objects.nonNull(x) && Objects.nonNull(y)) {
            return x.divide(y, 2, RoundingMode.HALF_UP);
        }

        return null;
    }

    /**
     * 小数转百分数，适用于优惠率
     *
     * @param x 小数
     * @return x * 100，单位为元的金额，保留2位小数
     */
    public static BigDecimal toPercentage(BigDecimal x) {
        if (Objects.nonNull(x)) {
            return x.multiply(new BigDecimal(100)).setScale(6, RoundingMode.HALF_UP);
        }

        return null;
    }

    /**
     * 百分数转小数，适用于优惠率
     *
     * @param x 百分数
     * @param scale 指定精度
     * @return x / y，
     */
    public static BigDecimal toDecimal(BigDecimal x, int scale) {
        if (Objects.nonNull(x)) {
            return x.divide(new BigDecimal(100), scale, RoundingMode.HALF_UP);
        }

        return null;
    }


    /**
     * 分转元
     *
     * @param cent 单位为分的金额
     * @return 单位为元的金额
     */
    public static String centToYuanStr(Integer cent) {
        BigDecimal bigDecimal = centIntegerToYuan(cent);
        if (Objects.isNull(bigDecimal)) {
            return null;
        }
        return bigDecimal.stripTrailingZeros().toPlainString();
    }

    /**
     * 分转元
     *
     * @param cent 单位为分的金额
     * @return 单位为元的金额
     */
    public static BigDecimal centIntegerToYuan(Integer cent) {
        if (Objects.nonNull(cent)) {
            return new BigDecimal(cent.toString()).movePointLeft(2);
        }
        return null;
    }

    public static String formatFee(BigDecimal f) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(f);
    }
}
