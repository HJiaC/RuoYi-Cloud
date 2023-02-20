import request from '@/utils/request'

// 查询用户钱包列表
export function listWallet(query) {
  return request({
    url: '/system/wallet/list',
    method: 'get',
    params: query
  })
}

// 查询用户钱包详细
export function getWallet(walletId) {
  return request({
    url: '/system/wallet/' + walletId,
    method: 'get'
  })
}

// 新增用户钱包
export function addWallet(data) {
  return request({
    url: '/system/wallet',
    method: 'post',
    data: data
  })
}

// 修改用户钱包
export function updateWallet(data) {
  return request({
    url: '/system/wallet',
    method: 'put',
    data: data
  })
}

// 删除用户钱包
export function delWallet(walletId) {
  return request({
    url: '/system/wallet/' + walletId,
    method: 'delete'
  })
}
