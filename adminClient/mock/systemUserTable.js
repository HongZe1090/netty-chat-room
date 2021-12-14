const Mock = require('mockjs')

const data = Mock.mock({
  'items|30': [{
    id: '@id(10)',
    username: '@cname',
    pwd: '@id(6)',
    role: '@cname',
    created_time: '@datetime',
    modify_time: '@datetime'
  }]
})

module.exports = [
  {
    url: '/vue-admin-template/SystemTable/list',
    type: 'get',
    response: config => {
      const items = data.items
      return {
        code: 20000,
        data: {
          total: items.length,
          items: items
        }
      }
    }
  }
]
