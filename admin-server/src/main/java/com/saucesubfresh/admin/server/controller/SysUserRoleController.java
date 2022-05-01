package com.saucesubfresh.admin.server.controller;


import com.saucesubfresh.admin.common.vo.Result;
import com.saucesubfresh.admin.server.dto.resp.SysRoleRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysUserRoleUpdateDTO;
import com.saucesubfresh.admin.server.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 09:27:19
 */
@RestController
@RequestMapping("/userRole")
public class SysUserRoleController {

  @Autowired
  private SysUserRoleService sysUserRoleService;

  /**
   * 根据用户查询用户有哪些角色
   * @param userId
   * @return
   */
  @GetMapping("/list/{userId}")
  public Result<List<SysRoleRespDTO>> listRoles(@PathVariable Long userId) {
    return Result.succeed(sysUserRoleService.listRoles(userId));
  }

  /**
   * 给用户分配角色
   */
  @PostMapping("/allocRole")
  public Result<Boolean> allocRole(@RequestBody @Valid SysUserRoleUpdateDTO sysUserRoleUpdateDTO) {
    return Result.succeed(sysUserRoleService.allocRole(sysUserRoleUpdateDTO));
  }

}
