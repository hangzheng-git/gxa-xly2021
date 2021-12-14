package com.gxa.gxaxly2021.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Comparable<Menu> {


    /**
     * 菜单的ID
     */
    private Integer menuId;

    /**
     * 菜单的名字
     */
    private String menuName;

    /**
     * 菜单跳转的地址
     */
    private String menuUrl;

    /**
     * 菜单的等级
     */
    private Integer menuLevel;

    /**
     * 菜单排序
     */
    private Integer menuOrder;


    /**
     * 子菜单
     */
    private List<Menu> subMenus = new ArrayList<>();

    public Menu(Integer menuId, String menuName, String menuUrl, Integer menuLevel) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.menuLevel = menuLevel;
    }

    public Menu(Integer menuId, String menuName, String menuUrl, Integer menuLevel, Integer menuOrder) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.menuLevel = menuLevel;
        this.menuOrder = menuOrder;
    }



    @Override
    public int compareTo(Menu o) {
        //降序排列
        return o.getMenuOrder()-this.getMenuOrder();
    }
}
