package com.saucesubfresh.admin.server.controller;

import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.common.vo.Result;
import com.saucesubfresh.admin.server.dto.create.SysUserCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysUserReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysUserRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysUserUpdateDTO;
import com.saucesubfresh.admin.server.service.SysUserService;
import com.saucesubfresh.starter.security.context.UserSecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
  public Result<Boolean> updateBatch(@RequestBody @Valid SysUserUpdateDTO sysUserUpdateDTO) {
    return Result.succeed(sysUserService.updateBatch(sysUserUpdateDTO));
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

  @PostMapping("/saveUser")
  public Result<SysUserRespDTO> saveUser(@RequestBody @Valid SysUserCreateDTO sysUserCreateDTO) {
    return Result.succeed(sysUserService.saveUser(sysUserCreateDTO));
  }


}
