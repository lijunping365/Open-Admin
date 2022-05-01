package com.saucesubfresh.admin.server.controller;

import com.saucesubfresh.admin.common.vo.Result;
import com.saucesubfresh.admin.server.dto.resp.SysResourceRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysRoleResourceUpdateDTO;
import com.saucesubfresh.admin.server.service.SysRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-30 11:44:55
 */
@Validated
@RestController
@RequestMapping("/roleResource")
public class SysRoleResourceController {

  @Autowired
  private SysRoleResourceService sysRoleResourceService;

  /**
   * 获取角色相关资源
   * @param roleId
   * @return
   */
  @GetMapping("/list/{roleId}")
  public Result<List<SysResourceRespDTO>> listResource(@PathVariable Long roleId) {
    return Result.succeed(sysRoleResourceService.listResource(roleId));
  }

  /**
   * 给角色分配资源
   * @param sysRoleResourceUpdateDTO
   * @return
   */
  @PostMapping("/allocResource")
  public Result<Boolean> allocResource(@RequestBody @Valid SysRoleResourceUpdateDTO sysRoleResourceUpdateDTO) {
    return Result.succeed(sysRoleResourceService.allocResource(sysRoleResourceUpdateDTO));
  }

}
