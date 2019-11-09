package org.huha.ruleengine.common;

/**
 * 全局公共异常返回值枚举类
 *
 * @author xujunqi
 * @date 2019/1/28 15:08
 */
public enum GlobalResponseStatusEnum {
    /**
     * "成功", "0000"
     */
    SUCCESS("成功", "0000"),
    /**
     * "失败", "0001"
     */
    FAILURE("失败", "0001"),
    /**
     * "未登录", "1001")
     */
    UNLOGIN("未登录", "1001"),
    /**
     * "未授权", "1002"
     */
    UNAUTH("未授权", "1002"),
    /**
     * "参数校验异常", "1101"
     */
    INVALID_PARAM("参数校验异常", "1101"),
    /**
     * "参数转换异常", "1102"
     */
    INVALID_PARAM_DATA_BIND("参数转换异常", "1102"),
    /**
     * "系统处理异常", "9999"
     */
    ERROR("系统处理异常", "9999");


    private String name;
    private String code;

    GlobalResponseStatusEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

}
