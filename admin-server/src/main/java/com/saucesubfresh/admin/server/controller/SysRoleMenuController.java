package com.saucesubfresh.admin.server.controller;

import com.saucesubfresh.admin.common.vo.Result;
import com.saucesubfresh.admin.server.dto.resp.SysMenuRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysRoleMenuUpdateDTO;
import com.saucesubfresh.admin.server.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 09:27:19
 */
@Validated
@RestController
@RequestMapping("/roleMenu")
public class SysRoleMenuController {

  @Autowired
  private SysRoleMenuService sysRoleMenuService;

  /**
   * 获取角色相关菜单
   * @param roleId
   * @return
   */
  @GetMapping("/list/{roleId}")
  public Result<List<SysMenuRespDTO>> listMenu(@PathVariable Long roleId) {
    return Result.succeed(sysRoleMenuService.listMenu(roleId));
  }

  /**
   * 给角色分配菜单
   */
  @PostMapping("/allocMenu")
  public Result<Boolean> allocMenu(@RequestBody @Valid SysRoleMenuUpdateDTO sysRoleMenuUpdateDTO) {
    return Result.succeed(sysRoleMenuService.allocMenu(sysRoleMenuUpdateDTO));
  }

}
