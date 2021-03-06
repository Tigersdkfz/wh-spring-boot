package com.sfa.controller.sys;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sfa.model.sys.MenuEntity;
import com.sfa.model.sys.PageResult;
import com.sfa.model.sys.UserEntity;
import com.sfa.service.sys.MenuService;
import com.sfa.service.sys.UserService;

@RestController
public class MenuController {

	private Logger log = LoggerFactory.getLogger(MenuController.class);

	@Resource(name = "menuServiceImpl")
	private MenuService menuService;

	@Resource(name = "userServiceImpl")
	private UserService userService;

	/**
	 * 获取该用户的菜单权限
	 * 
	 * @param loginName
	 * @return
	 */
	@GetMapping("/menu/{loginName}")
	public List<MenuEntity> menuList(@PathVariable String loginName) {
		UserEntity userEntity = userService.getUserEntityByLoginName(loginName);
		List<MenuEntity> menuList = menuService.menuList(1);
		return menuList;
	}

	/**
	 * 获取menus表数据
	 * 
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@GetMapping("/menus")
	public PageResult menusList(int pageSize, int page, String menuId) {
		PageResult pageResult = new PageResult();
		pageResult.setData(menuService.menusList(pageSize, page * pageSize, menuId));
		pageResult.setTotalCount(menuService.menusSize(pageSize, page * pageSize, menuId));
		log.debug("The method is ending");
		return pageResult;
	}

	/**
	 * 通过parentId得到menus列表
	 * 
	 * @param parentId
	 * @return
	 */
	@GetMapping("/menus/parentId")
	public List<MenuEntity> menusByParentId(int parentId) {
		return menuService.menusByParentId(parentId);
	}

	/**
	 * 新建菜单信息
	 * 
	 * @param menuEntity
	 * @return
	 */
	@PostMapping("/menus/menu")
	public MenuEntity insertMenu(@RequestBody MenuEntity menuEntity) {
		menuService.insertMenu(menuEntity);
		log.debug("The method is ending");
		return menuEntity;
	}

	/**
	 * 修改菜单信息
	 * 
	 * @param menuEntity
	 * @param id
	 * @return
	 */
	@PutMapping("/menus/{id}")
	public MenuEntity updateMenu(@RequestBody MenuEntity menuEntity, @PathVariable int id) {
		if (menuEntity.getId() == id) {
			menuService.updateMenu(menuEntity);
		}
		log.debug("The method is ending");
		return menuEntity;
	}

	/**
	 * 删除菜单信息
	 * 
	 * @param groupId
	 * @return
	 */
	@DeleteMapping("/menus")
	public List<String> deleteMenus(@RequestBody List<String> groupId) {
		menuService.deleteMenus(groupId);
		return groupId;
	}

	/**
	 * 获取二级菜单
	 * @return
	 */
	@GetMapping("/menus/submenus")
	public List<MenuEntity> getSubmenus() {
		return menuService.getSubmenus();
	}
	
	
	/**
	 * 1.获取该用户的菜单权限
	 * 
	 * @param loginName
	 * @return
	 */
	@PostMapping("/qureymenubyroleid")
	public List<MenuEntity> menuList(HttpServletRequest request, HttpServletResponse response,@RequestBody UserEntity userEntity)throws IOException {
		if(userEntity.getRoleId() !=null){
			List<MenuEntity> menuList = menuService.menuList(userEntity.getRoleId());
			return menuList;
		}
		else{
			throw new UnapprovedClientAuthenticationException("接受参数为空！");
		}
		
	}
}
