import request from '@/utils/request'

// 查询补贴发放列表
export function listSubsidy(query) {
  return request({
    url: '/system/subsidy/list',
    method: 'get',
    params: query
  })
}

// 查询补贴发放详细
export function getSubsidy(subsidyId) {
  return request({
    url: '/system/subsidy/' + subsidyId,
    method: 'get'
  })
}

// 新增补贴发放
export function addSubsidy(data) {
  return request({
    url: '/system/subsidy',
    method: 'post',
    data: data
  })
}

// 修改补贴发放
export function updateSubsidy(data) {
  return request({
    url: '/system/subsidy',
    method: 'put',
    data: data
  })
}

// 删除补贴发放
export function delSubsidy(subsidyId) {
  return request({
    url: '/system/subsidy/' + subsidyId,
    method: 'delete'
  })
}
