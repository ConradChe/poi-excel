package com.example.demo.dict;

public enum MaterialEnum {
    SUPPLIER_PHONE("supplier_phone","供应商电话"),
    MATERIAL_NAME("material_name","名称"),
    MODELS("models","规格"),
    OPERATE_TIME("operate_time","录入时间"),
    OPERATE_NAME("operate_name","操作人员"),
    PRICE("price","价格"),
    MATERIAL_CODE("material_code","编号"),
    STOCK_NUM("stock_num","库存"),
    SUPPLIER_NAME("supplier_name","供应商名称"),
    UNIT("unit","单位");

    private String code;
    private String desc;

    MaterialEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
