package com.shp.dev.network.common.util.python;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 调用python代码
 * @CreateTime: 2020/11/19 15:22
 * @PackageName: com.shp.dev.network.common.util
 * @ProjectName: network
 */
public class TransferPython {

    public static void main(String[] args) {

        String templatePath = Class.class.getResource("/python").getPath()+"/add.py";
        templatePath=templatePath.substring(1);

        PythonInterpreter interpreter=new PythonInterpreter();
        interpreter.exec("print('hello word')");

        interpreter.execfile(templatePath);

        // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        PyFunction pyFunction = interpreter.get("add", PyFunction.class);
        int a = 5, b = 10;
        //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
        PyObject pyobj = pyFunction.__call__(new PyInteger(a), new PyInteger(b));
        System.out.println("the anwser is: " + pyobj);




    }
}
