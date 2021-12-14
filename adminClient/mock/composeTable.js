const Mock = require('mockjs')

const data = Mock.mock({
  'items|30': [{
    id: '@id(5)',
    name: '@cname',
    description: '@sentence(10, 20)',
    associatedAttr: '@sentence(8)',
    created_time: '@datetime',
    modify_time: '@datetime'
  }]
})

module.exports = [
  {
    url: '/vue-admin-template/composeTable/list',
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
