package com.dyp.myssm.ioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ClassPathXmlApplicationContext implements BeanFactory{
    private Map<String,Object> beanMap = new HashMap<>();

    public ClassPathXmlApplicationContext(){

        try {
            //读取配置文件
            InputStream is = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            //获取documentBuilderFactory
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //获取documentBuilder
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //创建document对象（配置文件）
            Document document = documentBuilder.parse(is);

            //获取配置文件所有bean节点
            NodeList beanNodeList = document.getElementsByTagName("bean");

            //放到map中
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element)beanNode;

                    String beanId = beanElement.getAttribute("id");
                    String beanClass = beanElement.getAttribute("class");
                    Object beanObj = Class.forName(beanClass).newInstance();

                    //实例放入map中，bean与bean之间的依赖关系未设置
                    beanMap.put(beanId,beanObj);
                }
            }

            //组装bean与bean之间的依赖关系

            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    //获取到了bean节点 的emement
                    Element beanElement = (Element) beanNode;
                    //获取benid
                    String beanId = beanElement.getAttribute("id");
                    //
                    NodeList beanchildNodeList = beanElement.getChildNodes();
                    //遍历获取property属性
                    for (int j = 0; j < beanchildNodeList.getLength(); j++) {
                        Node beanChildNode = beanchildNodeList.item(j);

                        if (beanChildNode.getNodeType() == Element.ELEMENT_NODE && "property".equals(beanChildNode.getNodeName())){
                            Element propertyElement =(Element) beanChildNode;
                            String propertyName = propertyElement.getAttribute("name");
                            String propertyRef = propertyElement.getAttribute("ref");

                            //找到ref对象的实例
                            Object refObj = beanMap.get(propertyRef);

                            //获取要赋值的bean实例
                            Object beanObj = beanMap.get(beanId);

                            Class<?> beanClass = beanObj.getClass();
                            Field propertyField = beanClass.getDeclaredField(propertyName);

                            propertyField.setAccessible(true);
                            propertyField.set(beanObj,refObj);
                        }

                    }


                }
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }


    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
