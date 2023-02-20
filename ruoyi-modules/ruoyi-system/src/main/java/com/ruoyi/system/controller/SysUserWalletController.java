package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.SysUserWallet;
import com.ruoyi.system.service.ISysUserWalletService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * @filename: SysUserWalletController
 * @author: HXJ
 * @date: 2023/2/20 3:22 PM
 * @version: V1.0
 * @description:
 */
@RestController
@RequestMapping("/wallet")
public class SysUserWalletController extends BaseController
{
    @Autowired
    private ISysUserWalletService sysUserWalletService;

    /**
     * 查询用户钱包列表
     */
    @RequiresPermissions("system:wallet:list")
    @GetMapping("/list")
    public TableDataInfo list(SysUserWallet sysUserWallet)
    {
        startPage();
        List<SysUserWallet> list = sysUserWalletService.selectSysUserWalletList(sysUserWallet);
        return getDataTable(list);
    }

    /**
     * 导出用户钱包列表
     */
    @RequiresPermissions("system:wallet:export")
    @Log(title = "用户钱包", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysUserWallet sysUserWallet)
    {
        List<SysUserWallet> list = sysUserWalletService.selectSysUserWalletList(sysUserWallet);
        ExcelUtil<SysUserWallet> util = new ExcelUtil<SysUserWallet>(SysUserWallet.class);
        util.exportExcel(response, list, "用户钱包数据");
    }

    /**
     * 获取用户钱包详细信息
     */
    @RequiresPermissions("system:wallet:query")
    @GetMapping(value = "/{walletId}")
    public AjaxResult getInfo(@PathVariable("walletId") Long walletId)
    {
        return success(sysUserWalletService.selectSysUserWalletByWalletId(walletId));
    }

    /**
     * 新增用户钱包
     */
    @RequiresPermissions("system:wallet:add")
    @Log(title = "用户钱包", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserWallet sysUserWallet)
    {
        return toAjax(sysUserWalletService.insertSysUserWallet(sysUserWallet));
    }

    /**
     * 修改用户钱包
     */
    @RequiresPermissions("system:wallet:edit")
    @Log(title = "用户钱包", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserWallet sysUserWallet)
    {
        return toAjax(sysUserWalletService.updateSysUserWallet(sysUserWallet));
    }

    /**
     * 删除用户钱包
     */
    @RequiresPermissions("system:wallet:remove")
    @Log(title = "用户钱包", businessType = BusinessType.DELETE)
    @DeleteMapping("/{walletIds}")
    public AjaxResult remove(@PathVariable Long[] walletIds)
    {
        return toAjax(sysUserWalletService.deleteSysUserWalletByWalletIds(walletIds));
    }

    /**
     *
     */
}