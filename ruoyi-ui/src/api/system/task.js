import request from '@/utils/request'

// 查询Web端任务中心任务列表
export function listTask(query) {
  return request({
    url: '/system/task/list',
    method: 'get',
    params: query
  })
}

// 查询Web端任务中心任务详细
export function getTask(taskId) {
  return request({
    url: '/system/task/' + taskId,
    method: 'get'
  })
}

// 新增Web端任务中心任务
export function addTask(data) {
  return request({
    url: '/system/task',
    method: 'post',
    data: data
  })
}

// 修改Web端任务中心任务
export function updateTask(data) {
  return request({
    url: '/system/task',
    method: 'put',
    data: data
  })
}

//改变Web端任务中心任务状态
export function chageTaskStatus(taskId,status){
    return request({
        url: '/system/task/change/' + taskId + '/' + status,
        method: 'post' 
    })
}

// 删除Web端任务中心任务
export function delTask(taskId) {
  return request({
    url: '/system/task/' + taskId,
    method: 'delete'
  })
}
