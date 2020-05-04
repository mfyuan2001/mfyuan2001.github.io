package com.springmvc.hander;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.pojo.Account;
import com.springmvc.pojo.User;

@Controller
@RequestMapping("springmvc")
@SessionAttributes(types = {String.class})
public class Handler {
	public final String targetPage = "success";

	/**
	 * 控制器处理请求业务的方法的格式 public String 方法名称（）{}
	 * 
	 * String :当前方法处理完毕之后，所要返回的逻辑视图名称
	 */
	@RequestMapping(value = "/hello", method = RequestMethod.GET, params = { "id", "name=tom" })
	public String helloword() {
		System.out.println("走入到hello方法中。。。。。");
		return targetPage;
	}

	/**
	 * ? 匹配文件名中的一个字符 * 匹配文件名中的任意字符 ** ：** 匹配多层路径
	 */
	@RequestMapping("/**/antStyle")
	public String antStyle() {
		System.out.println("走入ant风格");
		return targetPage;
	}

	/**
	 * 访问的url /值
	 * 
	 * 配置映射信息：需要映射信息的后面对应的位置加入占位符「」
	 * 
	 * index.jsp: href="springmvc/testPathVar/12"
	 * 
	 * 如何将占位符绑定到请求处理方法当中
	 * 
	 * 配合请求处理方法的形参
	 * 
	 * @return
	 */
	@RequestMapping("/testPathVar/{id}")
	public String testPathVar(@PathVariable(value = "id") int id) {
		System.out.println(id);
		return targetPage;
	}

	@RequestMapping(value = "/testGET/{id}", method = RequestMethod.GET)
	public String testGET(@PathVariable(value = "id") Integer id) {
		System.out.println("正在获取id为" + id + "的用户信息");
		return targetPage;
	}

	@RequestMapping(value = "/testPOST", method = RequestMethod.POST)
	public String testPOST() {
		System.out.println("正在新建资源");
		return targetPage;
	}

	@RequestMapping(value = "/testDELETE/{id}", method = RequestMethod.DELETE)
	public String testDELETE(@PathVariable(value = "id") Integer id) {
		System.out.println("正在删除id为+" + id + "的用户信息");
		return targetPage;
	}

	@RequestMapping(value = "/testPUT/{id}", method = RequestMethod.PUT)
	public String testPUT(@PathVariable(value = "id") Integer id) {
		System.out.println("正在修改id为" + id + "的用户信息");
		return targetPage;
	}

	/**
	 * Springmvc是通过处理方法的形参与请求参数进行绑定 形参声明前面加入@RequestParam(value="前端控件的name属性")
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping("/testRequestParam")
	public String testRequestParam(@RequestParam(value = "id", required = true) Integer id, String name) {
		System.out.println(id);
		System.out.println(name);
		return targetPage;
	}

	@RequestMapping("/testPojo")
	public String testPojo(User user) {
		System.out.println(user);
		return targetPage;
	}

	@RequestMapping("/testRequestHander")
	public String testRequestHander(@RequestHeader("host") String host, @RequestHeader("Accept") String accept) {
		System.out.println(host);
		System.out.println(accept);
		return targetPage;
	}

	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID") String id) {
		System.out.println(id);
		return targetPage;
	}
	@RequestMapping("/testServletAPI")
	public String testServletAPI(HttpServletRequest request,HttpServletResponse response) {
		System.out.println(request);
		System.out.println(response);
		return targetPage;
	}
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(targetPage);
		//设置的值都给放到请求范围中去了
		mv.addObject("name", "tom");
		return mv;
	}
	@RequestMapping("/testMap")
	public String testMap(Map<String, Object> map) {
		map.put("age",18);
		return targetPage;
	}
	
	@RequestMapping("/testModel")
	public String testModel(Model model) {
		model.addAttribute("email", "1535659075@qq.com");
		return targetPage;
	}
	
	@RequestMapping("/testModelMap")
	public String testModelMap(ModelMap modelMap) {
		modelMap.addAttribute("city", "北京");
		return targetPage;
	}
	
	@RequestMapping("/testResult")
	public String testResult() {
		return "result";
	}
	@ModelAttribute(value="abc")
	public Account firstMethod() {
		//应该是从数据库中查询出来的原始信息
		Account account = new Account(101,"JIM","2016-01-01");
		return account;
	}
	/**
	 * 1.执行ModelAttribute修饰的方法
	 *        1 tom   2016-1-1  key = account
	 * 2.获取的原始数据放置到请求范围当中指定的key，对应的value
	 * 
	 * 3.pojo入参会从请求范围当中查找key对应的value对象，找到的话   对象的引用赋给入参
	 *       1   tom   2016-1-1
	 * 4.前端用户数据的值与业务方法的入参进行绑定
	 *       1 jim  2016-1-1
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(@ModelAttribute("abc")Account account) {
		System.out.println(account);
		return targetPage;
	}
	@RequestMapping("/testViewAndViewResolver")
	public String testViewAndViewResolver() {
		System.out.println("this is testViewAndResolver");
		return targetPage;
	}
	@RequestMapping("/testABC")
	public String testABC() {
		return "a";
	}
	
}
