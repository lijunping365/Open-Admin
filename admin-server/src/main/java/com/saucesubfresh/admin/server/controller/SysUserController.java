package com.saucesubfresh.admin.server.controller;

import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.common.vo.Result;
import com.saucesubfresh.admin.server.dto.create.SysUserCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysUserReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysUserRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysUserUpdateDTO;
import com.saucesubfresh.admin.server.service.SysUserService;
import com.saucesubfresh.admin.server.vo.MenuTreeVO;
import com.saucesubfresh.starter.security.context.UserSecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 管理员表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 09:27:19
 */
@Slf4j
@RestController
@Validated
@RequestMapping("/user")
public class SysUserController {

  @Autowired
  private SysUserService sysUserService;

  @GetMapping("/page")
  public Result<PageResult<SysUserRespDTO>> list(SysUserReqDTO sysUserReqDTO) {
    return Result.succeed(sysUserService.selectPage(sysUserReqDTO));
  }

  @PutMapping("/update")
  public Result<Void> update(@RequestBody @Valid SysUserUpdateDTO sysUserUpdateDTO) {
    sysUserService.update(sysUserUpdateDTO);
    return Result.succeed();
  }

  @GetMapping("/loadByUserId")
  public Result<SysUserRespDTO> loadUserByUserId(@RequestParam("userId") Long userId) {
    return Result.succeed(sysUserService.loadUserByUserId(userId));
  }

  @GetMapping("/currentUser")
  public Result<SysUserRespDTO> getCurrentUser() {
    Long userId = UserSecurityContextHolder.getUserId();
    return Result.succeed(sysUserService.loadUserByUserId(userId));
  }

  @GetMapping("/getUserMenus")
  public Result<List<MenuTreeVO>> getUserMenus() {
    Long userId = UserSecurityContextHolder.getUserId();
    return Result.succeed(sysUserService.getUserMenus(userId));
  }

  @PostMapping("/saveUser")
  public Result<Void> saveUser(@RequestBody @Valid SysUserCreateDTO sysUserCreateDTO) {
    sysUserService.saveUser(sysUserCreateDTO);
    return Result.succeed();
  }

  @PostMapping("/allocRole")
  public Result<Void> allocRole(@RequestBody @Valid SysUserUpdateDTO sysUserUpdateDTO) {
    sysUserService.allocRole(sysUserUpdateDTO);
    return Result.succeed();
  }
}
