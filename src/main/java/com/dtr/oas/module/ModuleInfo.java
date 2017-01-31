package com.dtr.oas.module;

import com.dtr.oas.util.Strings;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by safayat on 12/12/15.
 */
public class ModuleInfo {

    private String name;
    private String tableName;
    private Map<String,String> propertyMap;

    public Map<String, String> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, String> propertyMap) {
        this.propertyMap = propertyMap;
    }

    public String getName() {
        return name;
    }
    public String getCapilalName() {
        return StringUtils.capitalize(name);
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getType(String name) {
        if(propertyMap ==null){
            return propertyMap.get(name);
        }
        return null;
    }

    @Override
    public String toString() {
        return "ModuleInfo{" +
                "name='" + name + '\'' +
                ", propertyMap=" + propertyMap +
                '}';
    }

    public String getTableName() {
        return Strings.isNotEmpty(tableName) ? tableName : name;

    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


}
