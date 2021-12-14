const Mock = require('mockjs')

const data = Mock.mock({
  'items|30': [{
    id: '@id(10)',
    nickname: '@cname',
    personCharJSON: '@sentence(10, 20)',
    personalityJSON: '@sentence(10, 20)',
    valuesJSON: '@sentence(10, 20)',
    abilityJSON: '@sentence(10, 20)',
    skillJSON: '@sentence(10, 20)',
    knowledgeJSON: '@sentence(10, 20)',
    created_time: '@datetime',
    modify_time: '@datetime'
  }]
})

module.exports = [
  {
    url: '/vue-admin-template/userTable/list',
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
