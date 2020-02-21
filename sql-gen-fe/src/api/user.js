import request from '@/utils/request'
import da from "element-ui/src/locale/lang/da";
import {serviceBlog} from '@/utils/request'

export function login(data) {
  return request({
    url: '/api/user/login',
    method: 'post',
    data:data
  })
}

export function getInfo(token) {
  return request({
    url: '/api/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/api/user/logout',
    method: 'post'
  })
}

export function genSql(data) {
  let param = new FormData();
  param.append("json", JSON.stringify(data));
  return request({
    url: '/api/generateSql',
    method: 'post',
    data: param
  })
}

export function analyseJar(formData){
   return serviceBlog({
     url :'/api/jar/resolveJar.do',
     method:'post',
     data:formData,
     responseType: 'blob'
   })
}

