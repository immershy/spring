package com.cmcc.autowire;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by zmcc on 17/4/26.
 */
public class ClassPathAppContext {

    private List<BeanDefinition> list = new ArrayList<BeanDefinition>();

    private Map<String, BeanDefinition> map = new HashMap<String, BeanDefinition>();

    public ClassPathAppContext(String file) {
        // 读xml文件
        readXmlFile(file);
        // 实例化
        instantiateAllBeans();
        // 处理依赖,注解
        autowireField();
    }

    private void autowireField() {
        map.forEach((k, v) -> {
            try {
                String clzString = v.getClz();
                Class clz = Class.forName(clzString);
                for (Method method : clz.getMethods()) {
                    List<Annotation> annotations = Arrays.asList(method.getAnnotations());
                    annotations.forEach(a -> {
                        System.out.println(a.annotationType());
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void instantiateAllBeans() {

        list.forEach(def -> {
            try {
                if (!map.containsKey(def.getId())) {
                    def.setInstance(Class.forName(def.getClz()).newInstance());
                    map.put(def.getId(), def);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void readXmlFile(String file) {
        try {

            SAXReader reader = new SAXReader();
            Document document = reader.read(this.getClass().getClassLoader().getResourceAsStream(file));
            Element element = document.getRootElement();

            Iterator<Element> iterator = element.elementIterator();

            while (iterator.hasNext()) {
                Element ele = iterator.next();
                String id = ele.attributeValue("id");
                String clz = ele.attributeValue("class");
                BeanDefinition definition = new BeanDefinition();
                definition.setClz(clz);
                definition.setId(id);
                list.add(definition);
            }


        } catch (Exception e) {

        }

    }

    public Object getBean(String a) {
        return null;
    }
}


class BeanDefinition {
    private String id;

    private String clz;

    private Object instance;

    public String getClz() {
        return clz;
    }

    public String getId() {
        return id;
    }

    public void setClz(String clz) {
        this.clz = clz;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }
}