const Mock = require('mockjs')

const data = Mock.mock({
  'items|30': [{
    code: '@id(10)',
    name: '@name',
    property: '@sentence(10, 20)',
    created_time: '@datetime',
    modify_time: '@datetime'
  }]
})

module.exports = [
  {
    url: '/vue-admin-template/Property/list',
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
