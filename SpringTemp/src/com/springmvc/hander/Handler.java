package com.springmvc.hander;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("springmvc")
public class Handler {
	public final String targetPage ="success";
    /**
     * 控制器处理请求业务的方法的格式
     * public String 方法名称（）{}
     * 
     * String  :当前方法处理完毕之后，所要返回的逻辑视图名称
     */
	@RequestMapping(value="/hello",method=RequestMethod.GET,params={"id","name=tom"})
	public String helloword() {
		System.out.println("走入到hello方法中。。。。。");
		return targetPage;
	}
	/**
	 *  ?   匹配文件名中的一个字符
	 *  *   匹配文件名中的任意字符
	 *  ** ：** 匹配多层路径 
	 */
	@RequestMapping("/**/antStyle")
	public String antStyle() {
		System.out.println("走入ant风格");
		return targetPage;
	}
	/**
	 * 访问的url /值
	 *  
	 *  配置映射信息：需要映射信息的后面对应的位置加入占位符「」
	 * 
	 * index.jsp:
	 * href="springmvc/testPathVar/12"
	 * 
	 * 如何将占位符绑定到请求处理方法当中
	 * 
	 * 配合请求处理方法的形参
	 * @return
	 */
	@RequestMapping("/testPathVar/{id}")
	public String testPathVar(@PathVariable(value="id") int id) {
		System.out.println(id);
		return targetPage;
	}
	@RequestMapping(value="/testGET/{id}",method =RequestMethod.GET)
	public String  testGET(@PathVariable(value="id") Integer id) {
		System.out.println("正在获取id为"+id+"的用户信息");
		return targetPage;
	}
	@RequestMapping(value="/testPOST",method = RequestMethod.POST)
	public String testPOST() {
		System.out.println("正在新建资源");
		return targetPage;
	}
	@RequestMapping(value = "/testDELETE/{id}",method = RequestMethod.DELETE)
	public String testDELETE(@PathVariable(value="id") Integer id) {
		System.out.println("正在删除id为+"+id+"的用户信息");
		return targetPage;
	}
	@RequestMapping(value = "/testPUT/{id}",method = RequestMethod.PUT)
	public String testPUT(@PathVariable(value="id") Integer id) {
		System.out.println("正在修改id为"+id+"的用户信息");
		return targetPage;
	}
	
	/**
	 * Springmvc是通过处理方法的形参与请求参数进行绑定
	 * 形参声明前面加入@RequestParam(value="前端控件的name属性")
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping("/testRequestParam")
	public String testRequestParam(@RequestParam(value="id",required=true)Integer id,String name) {
		System.out.println(id);
		System.out.println(name);
		return targetPage;
	}
}
