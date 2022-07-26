package com.dyp.fruit.controllers;

import com.dyp.fruit.pojo.Fruit;
import com.dyp.fruit.service.FruitService;
import com.dyp.myssm.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public class FruitController {

    private FruitService fruitService = null;


    private String index(Integer pageNo, String oper,String keyword,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (pageNo == null){
            pageNo = 1;
        }

        //分情况获取pageNo和keyword
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)){
            pageNo = 1;
            if (StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            session.setAttribute("keyword",keyword);
        }else {
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null){
                keyword = (String) keywordObj;
            }else {
                keyword = "";
            }
        }
        session.setAttribute("pageNo",pageNo);
        List<Fruit> fruitList = fruitService.getFruitList(pageNo, keyword);
        session.setAttribute("fruitList", fruitList);

        Integer fruitCount = fruitService.getFruitCount(keyword);
        int pageCount = (fruitCount +5 - 1)/5;
        session.setAttribute("pageCount",pageCount);

        return "index";

    }



    private String add(String fname,BigDecimal price,Integer fcount,String remark) {

        Fruit fruit = new Fruit(null, fname, price, fcount, remark);
        fruitService.addFruit(fruit);

//        resp.sendRedirect("fruit.do");
        return "redirect:fruit.do";

    }

    private String del(Integer fid,HttpServletRequest req, HttpServletResponse resp) {
        if (fid != null){
            fruitService.delFruit(fid);
            return "redirect:fruit.do";

        }
        return "error";

    }



    private String edit(String fid,HttpServletRequest req, HttpServletResponse resp)  {
        if (fid != null){
            Fruit fruit = fruitService.eidtFruit(fid);
            req.setAttribute("fruit",fruit);
            return "edit";

        }
        return "error";
    }


    private String update(Integer fid , String fname , BigDecimal price , Integer fcount , String remark )  {
        Fruit fruit = new Fruit(fid, fname, price, fcount, remark);

        fruitService.updateFruit(fruit);

//        super.processTemplate("index",req,resp);

        return "redirect:fruit.do";


    }









}
